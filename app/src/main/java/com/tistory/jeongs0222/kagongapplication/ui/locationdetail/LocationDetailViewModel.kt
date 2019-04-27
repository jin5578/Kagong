package com.tistory.jeongs0222.kagongapplication.ui.locationdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringLocationDetail.BringLocationDetailResult
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

    private val _locationDetailItem = MutableLiveData<BringLocationDetailResult>()
    val locationDetailItem: LiveData<BringLocationDetailResult>
        get() = _locationDetailItem

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

    }

    fun previousClickEvent() {
        _previousClick.call()
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

    fun bringLocationDetail() {
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

    fun locationLikeClick() {
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