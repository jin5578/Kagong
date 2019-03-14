package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailTabRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailTabViewModel(private val areaDetailTabRepository: AreaDetailTabRepository): DisposableViewModel(), AreaDetailTabEventListener {

    private val _areaLocation = MutableLiveData<MutableList<BringAreaLocationResult>>()
    val areaLocation: LiveData<MutableList<BringAreaLocationResult>>
        get() = _areaLocation

    private val TAG = "AreaDetailViewModel"


    fun bringAreaLocation(area: String) {
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

    override fun areaLocationClickEvent(order: Int) {
        Log.e(TAG, order.toString())
    }

}

interface AreaDetailTabEventListener {

    fun areaLocationClickEvent(order: Int)

}