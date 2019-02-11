package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.RegisterRepository
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.tistory.jeongs0222.kagongapplication.model.dump.year.YearItem
import com.tistory.jeongs0222.kagongapplication.ui.view.activity.MainActivity
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider


class RegisterViewModel(private val registerRepository: RegisterRepository) : DisposableViewModel(), RegisterEventListener {

    //RegisterActivity
    private val _userkey = MutableLiveData<String>()
    val userkey: LiveData<String>
        get() = _userkey

    private val _token = MutableLiveData<String>()
    val token: LiveData<String>
        get() = _token


    //BasicInfoFragment
    private val _nextClick = SingleLiveEvent<Any>()
    val nextClick: LiveData<Any>
        get() = _nextClick

    private val _validateClick = SingleLiveEvent<Any>()
    val validateClick: LiveData<Any>
        get() = _validateClick


    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname


    //PersonalInfoFragment
    private val _femaleClick = SingleLiveEvent<Any>()
    val femaleClick: LiveData<Any>
        get() = _femaleClick

    private val _maleClick = SingleLiveEvent<Any>()
    val maleClick: LiveData<Any>
        get() = _maleClick

    private val _ageClick = SingleLiveEvent<Any>()
    val ageClick: LiveData<Any>
        get() = _ageClick

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _confirmClick = SingleLiveEvent<Any>()
    val confirmClick: LiveData<Any>
        get() = _confirmClick


    //YearFragment
    private val _yearItem = MutableLiveData<MutableList<YearItem>>()
    val yearItem: LiveData<MutableList<YearItem>>
        get() = _yearItem


    private val _userSex = MutableLiveData<String>()
    val userSex: LiveData<String>
        get() = _userSex

    private val _userYear = MutableLiveData<String>()
    val userYear: LiveData<String>
        get() = _userYear


    private val TAG = "RegisterViewModel"

    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider
    private lateinit var dbHelperProvider: DBHelperProvider

    var validateCheck: Boolean = false


    init {
        Log.e(TAG, "RegisterViewModelCreated")

        getKey()
        getToken()
        getYearList()
    }

    fun bind(messageProvider: MessageProvider, intentProvider: IntentProvider, dbHelperProvider: DBHelperProvider) {
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider
        this.dbHelperProvider = dbHelperProvider
    }

    fun nextClickEvent() {
        _nextClick.call()

        Log.e(TAG, "test1Click")
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "test2Click")
    }

    fun confirmClickEvent() {
        _confirmClick.call()

        Log.e(TAG, "confirmClick")
    }

    fun validateClickEvent() {
        _validateClick.call()

        Log.e(TAG, "validateClick")
    }

    fun femaleClickEvent() {
        _femaleClick.call()

        _userSex.value = "female"
    }

    fun maleClickEvent() {
        _maleClick.call()

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
    fun register(userkey: String, token: String, nickname: String, sex: String, age: String) {
        registerRepository
            .register(userkey, token, nickname, sex, age)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                when {
                    it.value == 0 -> {
                        messageProvider.toastMessage(it.message)

                        dbHelperProvider.getDBHelper().insertUser(userkey)

                        intentProvider.intent(MainActivity::class.java)
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

    private fun getKey() {
        _userkey.value = FirebaseAuth.getInstance().uid

        Log.e(TAG, _userkey.value)
    }

    private fun getToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                _token.value = task.result!!.token
            })
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
