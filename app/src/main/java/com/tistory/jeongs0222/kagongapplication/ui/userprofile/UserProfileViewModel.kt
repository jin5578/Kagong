package com.tistory.jeongs0222.kagongapplication.ui.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringUserProfile.BringUserProfileResult
import com.tistory.jeongs0222.kagongapplication.model.repository.UserProfileRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserProfileViewModel(private val userProfileRepository: UserProfileRepository): DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _userProfileItem = MutableLiveData<BringUserProfileResult>()
    val userProfileItem: LiveData<BringUserProfileResult>
        get() = _userProfileItem


    fun previousClickEvent() {
        _previousClick.call()
    }

    fun bringUserProfile(userid: String) {
        userProfileRepository.bringUserProfile(userid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userProfileItem.value = it
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}