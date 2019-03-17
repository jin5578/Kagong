package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailTabRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailTabViewModel(private val areaDetailTabRepository: AreaDetailTabRepository): DisposableViewModel(),
    AreaDetailTabEventListener {

    //Entire
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _searchClick = SingleLiveEvent<Any>()
    val searchClick: LiveData<Any>
        get() = _searchClick


    //TourismFragment
    private val _areaLocation = MutableLiveData<MutableList<BringAreaLocationResult>>()
    val areaLocation: LiveData<MutableList<BringAreaLocationResult>>
        get() = _areaLocation


    //GoodPlaceFragment
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
    }

    fun searchClickEvent() {
        _searchClick.call()
    }

    fun bringAreaLocation(sort: Int, findlocation: String) {
        areaDetailTabRepository.bringAreaLocation(area, sort, findlocation)
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

    fun bringAreaGoodPlace(sort: Int, findGoodPlace: String) {
        areaDetailTabRepository.bringAreaGoodPlace(area, sort, findGoodPlace)
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