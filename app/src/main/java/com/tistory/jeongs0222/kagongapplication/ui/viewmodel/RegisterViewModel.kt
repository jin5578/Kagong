package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.RegisterRepository
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RegisterViewModel(private val registerRepository: RegisterRepository) : DisposableViewModel(),
    RegisterEventListener {

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

    /*private val _validateCheck = MutableLiveData<Boolean>()
    val validateCheck: LiveData<Boolean>
        get() = _validateCheck*/


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


    private val _userSex = MutableLiveData<String>()
    val userSex: LiveData<String>
        get() = _userSex

    private val _userYear = MutableLiveData<String>()
    val userYear: LiveData<String>
        get() = _userYear


    private val TAG = "RegisterViewModel"

    private lateinit var messageProvider: MessageProvider

    var validateCheck: Boolean = false


    init {
        Log.e(TAG, "RegisterViewModelCreated")
    }

    fun bind(messageProvider: MessageProvider) {
        this.messageProvider = messageProvider
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

    override fun clickEvent(year: String) {
        _userYear.value = year
    }
}

interface RegisterEventListener {

    fun clickEvent(year: String)

}
