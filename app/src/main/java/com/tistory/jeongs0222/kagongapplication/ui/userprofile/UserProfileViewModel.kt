package com.tistory.jeongs0222.kagongapplication.ui.userprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringTOPSchedule.BringTOPScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringUserProfile.BringUserProfileResult
import com.tistory.jeongs0222.kagongapplication.model.repository.UserProfileRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserProfileViewModel(private val userProfileRepository: UserProfileRepository) : DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _userProfileItem = MutableLiveData<BringUserProfileResult>()
    val userProfileItem: LiveData<BringUserProfileResult>
        get() = _userProfileItem

    private val _userSchedule = MutableLiveData<MutableList<BringTOPScheduleResult>>()
    val userSchedule: LiveData<MutableList<BringTOPScheduleResult>>
        get() = _userSchedule

    private val _likeStatus = MutableLiveData<Int>()
    val likeStatus: LiveData<Int>
        get() = _likeStatus

    private val _likeCount = MutableLiveData<String>()
    val likeCount: LiveData<String>
        get() = _likeCount

    private lateinit var userKey: String
    private lateinit var theOtherPersonId: String


    init {
        _likeStatus.value = 0
    }

    fun bind(dbHelperProvider: DBHelperProvider, tOP: String) {
        userKey = dbHelperProvider.getDBHelper().getUserKey()
        this.theOtherPersonId = tOP

        bringUserProfile()
        bringTOPSchedule()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun userLikeClickEvent() {
        userLikeClick()
    }

    //해당 유저정보 보여주기
    private fun bringUserProfile() {
        userProfileRepository.bringUserProfile(theOtherPersonId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userProfileItem.value = it
                _likeCount.value = it.likecount
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun userLikeClick() {
        userProfileRepository.userLikeClick(userKey, theOtherPersonId, _likeStatus.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 2) {
                    _likeStatus.value = 0
                    _likeCount.value = (_likeCount.value!!.toInt() - 1).toString()
                } else if (it.value == 0) {
                    _likeStatus.value = 1
                    _likeCount.value = (_likeCount.value!!.toInt() + 1).toString()
                }

            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun bringTOPSchedule() {
        userProfileRepository.bringTOPSchedule(theOtherPersonId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userSchedule.value = it.bringTOPSchedule
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun userLikeValidate() {
        userProfileRepository.userLikeValidate(userKey, theOtherPersonId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _likeStatus.value = it.value
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}