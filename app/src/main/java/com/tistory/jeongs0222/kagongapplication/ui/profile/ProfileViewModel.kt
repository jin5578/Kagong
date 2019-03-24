package com.tistory.jeongs0222.kagongapplication.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.ProfileRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProfileViewModel(private val profileRepository: ProfileRepository): DisposableViewModel() {

    private val _detailPreviousClick = SingleLiveEvent<Any>()
    val profileDetailPreviousClick: LiveData<Any>
        get() = _detailPreviousClick

    private val _modifyClick = SingleLiveEvent<Any>()
    val modifyClick: LiveData<Any>
        get() = _modifyClick

    private val _validateClick = SingleLiveEvent<Any>()
    val validateClick: LiveData<Any>
        get() = _validateClick

    private val _saveClick = SingleLiveEvent<Any>()
    val saveClick: LiveData<Any>
        get() = _saveClick

    private val _modifyPreviousClick = SingleLiveEvent<Any>()
    val modifyPreviousClick: LiveData<Any>
        get() = _modifyPreviousClick

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String>
        get() = _nickname

    private val _introduce = MutableLiveData<String>()
    val introduce: LiveData<String>
        get() = _introduce

    private val _finishRequest = SingleLiveEvent<Int>()
    val finishRequest: LiveData<Int>
        get() = _finishRequest


    private lateinit var messageProvider: MessageProvider

    var validateCheck: Boolean = false

    var profileModified: Boolean = false


    init {
        bringNicknameAndIntro()
    }

    fun bind(messageProvider: MessageProvider) {
        this.messageProvider = messageProvider
    }


    fun detailPreviousClickEvent() {
        _detailPreviousClick.call()
    }

    fun modifyClickEvent() {
        _modifyClick.call()
    }

    fun modifyPreviousClickEvent() {
        _modifyPreviousClick.call()
    }

    fun saveClickEvent() {
        _saveClick.call()
    }

    fun validateClickEvent() {
        _validateClick.call()
    }

    fun bringNicknameAndIntro() {
        profileRepository.bringNicknameAndIntro(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _nickname.value = it.nickname

                _introduce.value = it.introduce
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun nicknameValidate(nickname: String) {
        profileRepository.nicknameValidate(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                when (it.value) {
                    0 -> {
                        messageProvider.toastMessage(it.message)
                        validateCheck = true
                    }

                    1 -> {
                        messageProvider.toastMessage(it.message)
                        validateCheck = false
                    }

                    2 -> {
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

    fun updateProfile(nickname: String, introduce: String) {
        profileRepository
            .updateProfile(
                uid,
                nickname,
                introduce
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

                    profileModified = true

                    _finishRequest.value = 0
                } else {
                    messageProvider.toastMessage(it.message)
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun validateMessage() {
        messageProvider.toastMessage("중복체크를 먼저 해주세요.")
    }
}