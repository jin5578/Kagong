package com.tistory.jeongs0222.kagongapplication.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.ProfileRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.ArrayList
import java.util.HashMap


class ProfileViewModel(private val profileRepository: ProfileRepository) : DisposableViewModel() {

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

    private val _imageClick = SingleLiveEvent<Any>()
    val imageClick: LiveData<Any>
        get() = _imageClick

    private val _finishRequest = SingleLiveEvent<Int>()
    val finishRequest: LiveData<Int>
        get() = _finishRequest

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String>
        get() = _nickname

    private val _introduce = MutableLiveData<String>()
    val introduce: LiveData<String>
        get() = _introduce

    private val _userImage = MutableLiveData<String>()
    val userImage: LiveData<String>
        get() = _userImage

    private val _uploadImageValue = MutableLiveData<Int>()
    val uploadImageValue: LiveData<Int>
        get() = _uploadImageValue


    private lateinit var messageProvider: MessageProvider

    private lateinit var userKey: String

    private var parts: MutableList<MultipartBody.Part> = ArrayList()

    var validateCheck: Boolean = true

    var profileModified: Boolean = false


    fun bind(messageProvider: MessageProvider, dbHelperProvider: DBHelperProvider) {
        this.messageProvider = messageProvider

        userKey = dbHelperProvider.getDBHelper().getUserKey()

        bringNicknameAndIntro()
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

    fun imageClickEvent() {
        _imageClick.call()
    }

    fun bringNicknameAndIntro() {
        profileRepository.bringNicknameAndIntro(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _nickname.value = it.nickname

                _introduce.value = it.introduce

                _userImage.value = it.image
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
                userKey,
                nickname,
                introduce
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
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

    fun uploadProfileImage(file: File) {
        parts.add(prepareFilePart(file))

        profileRepository
            .uploadProfileImage(
                parts,
                getData()
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    _uploadImageValue.value = 0
                } else {
                    _uploadImageValue.value = 1

                    messageProvider.toastMessage(it.message)
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun prepareFilePart(file: File): MultipartBody.Part {
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        return MultipartBody.Part.createFormData("up_image[]", file.name, requestBody)
    }

    private fun getData(): HashMap<String, RequestBody> {
        return HashMap<String, RequestBody>().apply {
            this["googlekey"] = toRequestBody(userKey)
        }
    }

    private fun toRequestBody(value: String): RequestBody =
        RequestBody.create(MediaType.parse("text/plain"), value)

    fun validateMessage() {
        messageProvider.toastMessage("중복체크를 먼저 해주세요.")
    }
}