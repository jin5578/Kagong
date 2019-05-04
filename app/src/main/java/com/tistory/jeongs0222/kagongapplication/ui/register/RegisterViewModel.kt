package com.tistory.jeongs0222.kagongapplication.ui.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.RegisterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.tistory.jeongs0222.kagongapplication.model.dump.year.YearItem
import com.tistory.jeongs0222.kagongapplication.ui.main.MainActivity
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*


class RegisterViewModel(private val registerRepository: RegisterRepository) : DisposableViewModel(),
    RegisterEventListener {

    private val _validateClick = SingleLiveEvent<Any>()
    val validateClick: LiveData<Any>
        get() = _validateClick

    private val _ageClick = SingleLiveEvent<Any>()
    val ageClick: LiveData<Any>
        get() = _ageClick

    private val _confirmClick = SingleLiveEvent<Any>()
    val confirmClick: LiveData<Any>
        get() = _confirmClick

    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname

    private val _userSex = MutableLiveData<String>()
    val userSex: LiveData<String>
        get() = _userSex

    private val _yearItem = MutableLiveData<MutableList<YearItem>>()
    val yearItem: LiveData<MutableList<YearItem>>
        get() = _yearItem

    private val _userYear = MutableLiveData<String>()
    val userYear: LiveData<String>
        get() = _userYear


    private val TAG = "RegisterViewModel"

    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider
    private lateinit var dbHelperProvider: DBHelperProvider

    private lateinit var userKey: String
    private lateinit var loginMethod: String
    private lateinit var token: String

    var validateCheck: Boolean = false


    init {
        getYearList()
    }

    fun bind(messageProvider: MessageProvider, intentProvider: IntentProvider, dbHelperProvider: DBHelperProvider, userKey: String, loginMethod: String) {
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider
        this.dbHelperProvider = dbHelperProvider

        this.userKey = userKey
        this.loginMethod = loginMethod

        getToken()
    }


    fun confirmClickEvent() {
        _confirmClick.call()
    }

    fun validateClickEvent() {
        _validateClick.call()
    }

    fun femaleClickEvent() {
        _userSex.value = "female"
    }

    fun maleClickEvent() {
        _userSex.value = "male"
    }

    fun ageClickEvent() {
        _ageClick.call()
    }

    fun nicknameValidate(nickname: String) {
        registerRepository
            .nicknameValidate(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Log.e(TAG, "1")
                when {
                    it.value == 0 -> {
                        messageProvider.toastMessage(it.message)
                        _userNickname.value = nickname
                        validateCheck = true
                    }
                    it.value == 1 -> {
                        messageProvider.toastMessage(it.message)
                        validateCheck = false
                    }
                    else -> {
                        messageProvider.toastMessage(it.message)
                        validateCheck = false
                    }
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    @SuppressLint("CheckResult")
    fun register() {
        registerRepository
            .register(userKey, token, _userNickname.value!!, _userSex.value!!, _userYear.value!!, loginMethod)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                when {
                    it.value == 0 -> {
                        messageProvider.toastMessage(it.message)

                        dbHelperProvider.getDBHelper().insertUser(userKey)

                        intentProvider.finishIntent(MainActivity::class.java)
                    }

                    it.value == 1 -> {
                        messageProvider.toastMessage(it.message)
                    }
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun getToken() {
        if(loginMethod == "Google") {
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    token = task.result!!.token
                })
        } else {
            token = ""
        }

    }

    private fun getYearList() {
        val items = mutableListOf<YearItem>()

        var year = 1950

        for(i in 0..69) {
            items.add(i, YearItem(year.toString()))

            year++
        }

        _yearItem.value = items
    }

    override fun clickEvent(year: String) {
        _userYear.value = year
    }
}

interface RegisterEventListener {
    fun clickEvent(year: String)
}
