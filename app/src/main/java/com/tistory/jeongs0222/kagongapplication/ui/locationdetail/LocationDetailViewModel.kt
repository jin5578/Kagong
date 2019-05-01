package com.tistory.jeongs0222.kagongapplication.ui.locationdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringLocationDetail.BringLocationDetailResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringOperatingTime.BringOperatingTimeResult
import com.tistory.jeongs0222.kagongapplication.model.repository.LocationDetailRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LocationDetailViewModel(private val locationDetailRepository: LocationDetailRepository): DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _locationClick = SingleLiveEvent<Any>()
    val locationClick: LiveData<Any>
        get() = _locationClick

    private val _reviewWriteClick = SingleLiveEvent<Any>()
    val reviewWriteClick: LiveData<Any>
        get() = _reviewWriteClick

    private val _reviewMoreClick = SingleLiveEvent<Any>()
    val reviewMoreClick: LiveData<Any>
        get() = _reviewMoreClick

    private val _locationDetailItem = MutableLiveData<BringLocationDetailResult>()
    val locationDetailItem: LiveData<BringLocationDetailResult>
        get() = _locationDetailItem

    private val _operatingTime = MutableLiveData<MutableList<BringOperatingTimeResult>>()
    val operatingTime: LiveData<MutableList<BringOperatingTimeResult>>
        get() = _operatingTime

    private val _locationReviewItem = MutableLiveData<MutableList<BringLocationReviewResult>>()
    val locationReviewItem: LiveData<MutableList<BringLocationReviewResult>>
        get() = _locationReviewItem

    private val _likeStatus = MutableLiveData<Int>()
    val likeStatus: LiveData<Int>
        get() = _likeStatus

    private var order = 0
    private lateinit var userkey: String

    init {
        _likeStatus.value = 0
    }


    fun bind(order: Int, dbHelperProvider: DBHelperProvider) {
        this.order = order
        userkey = dbHelperProvider.getDBHelper().getUserKey()

        bringLocationDetail()
        bringOperatingTime()
        bringLocationReview()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun locationClickEvent() {
        _locationClick.call()
    }

    fun reviewWriteClickEvent() {
        _reviewWriteClick.call()
    }

    fun reviewMoreClickEvent() {
        _reviewMoreClick.call()
    }

    fun likeClickEvent() {
        locationLikeClick()
    }

    fun locationLikeValidate() {
        locationDetailRepository.locationLikeValidate(userkey, order)
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

    private fun bringLocationDetail() {
        locationDetailRepository.bringLocationDetail(order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _locationDetailItem.value = it
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun bringOperatingTime() {
        locationDetailRepository.bringOperatingTime(order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _operatingTime.value = it.bringOperatingTime
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }


    private fun bringLocationReview() {
        locationDetailRepository.bringLocationReview(order, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _locationReviewItem.value = it.bringLocationReview
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun locationLikeClick() {
        locationDetailRepository.locationLikeClick(userkey, order, _likeStatus.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 2) {
                    _likeStatus.value = 0
                } else if(it.value == 0) {
                    _likeStatus.value = 1
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}