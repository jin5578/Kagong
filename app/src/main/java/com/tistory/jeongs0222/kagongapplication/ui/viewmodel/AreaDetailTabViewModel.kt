package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailTabRepository
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailTabViewModel(private val areaDetailTabRepository: AreaDetailTabRepository): DisposableViewModel(), AreaDetailTabEventListener {

    //Entire
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _fragmentPosition = MutableLiveData<Int>()
    val fragmentPosition: LiveData<Int>
        get() = _fragmentPosition


    //SearchFragment
    private val _closeClick = SingleLiveEvent<Any>()
    val closeClick: LiveData<Any>
        get() = _closeClick


    //TourismFragment
    private val _searchLocationClick = SingleLiveEvent<Any>()
    val searchLocationClick: LiveData<Any>
        get() = _searchLocationClick

    private val _areaLocation = MutableLiveData<MutableList<BringAreaLocationResult>>()
    val areaLocation: LiveData<MutableList<BringAreaLocationResult>>
        get() = _areaLocation


    //GoodPlaceFragment
    private val _searchGoodPlaceClick = SingleLiveEvent<Any>()
    val searchGoodPlace: LiveData<Any>
        get() = _searchGoodPlaceClick

    private val _areaGoodPlace = MutableLiveData<MutableList<BringAreaGoodPlaceResult>>()
    val areaGoodPlace: LiveData<MutableList<BringAreaGoodPlaceResult>>
        get() = _areaGoodPlace


    private lateinit var area: String


    private val TAG = "AreaDetailViewModel"

    fun bind(area: String) {
        this.area = area
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }

    fun closeClickEvent() {
        _closeClick.call()
    }

    fun searchLocationClickEvent() {
        _searchLocationClick.call()

        _fragmentPosition.value = 1
    }

    fun searchGoodPlaceClickEvent() {
        _searchGoodPlaceClick.call()

        _fragmentPosition.value = 2
    }

    fun bringAreaLocation() {
        Log.e("bringAreaLocation", "call")
        areaDetailTabRepository.bringAreaLocation(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaLocation.value = it.bringAreaLocation
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAreaGoodPlace() {
        areaDetailTabRepository.bringAreaGoodPlace(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaGoodPlace.value = it.bringAreaGoodPlace
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    override fun areaLocationClickEvent(order: Int) {
        Log.e(TAG, order.toString())
    }

}

interface AreaDetailTabEventListener {

    fun areaLocationClickEvent(order: Int)

}