package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailRepository
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailViewModel(private val areaDetailRepository: AreaDetailRepository): DisposableViewModel(), AreaDetailEventListener {


    //AreaDetailActivity
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _validateSchedue = MutableLiveData<String>()
    val validateSchedule: LiveData<String>
        get() = _validateSchedue


    //InformationFragment
    private val _areaInformation = MutableLiveData<MutableList<AreaInformationResult>>()
    val areaInformation: LiveData<MutableList<AreaInformationResult>>
        get() = _areaInformation

    private val _addScheduleClick = SingleLiveEvent<Any>()
    val addScheduleClick: LiveData<Any>
        get() = _addScheduleClick

    //TourismFragment
    private val _areaLocation = MutableLiveData<MutableList<BringAreaLocationResult>>()
    val areaLocation: LiveData<MutableList<BringAreaLocationResult>>
        get() = _areaLocation


    private val TAG = "AreaDetailViewModel"


    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }

    fun addScheduleClickEvent() {
        _addScheduleClick.call()

        Log.e(TAG, "addScheduleClick")
    }

    fun bringAreaInformation(area: String) {
        Log.e("bringAreaInformation", "call")
        areaDetailRepository.bringAreaDetail(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaInformation.value = it.bringinformation
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAreaLocation(area: String) {
        Log.e("bringAreaLocation", "call")
        areaDetailRepository.bringAreaLocation(area)
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

    fun validateSchedule(area: String) {
        areaDetailRepository.validateSchedule(uid, area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    _validateSchedue.value = "일정을 추가해보세요"
                } else {
                    _validateSchedue.value = it.date.removeRange(13, 18).replace("-", ".")
                }
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

interface AreaDetailEventListener {

    fun areaLocationClickEvent(order: Int)

}
