package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.accompanylist.AccompanyListResult
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailRepository
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailViewModel(private val areaDetailRepository: AreaDetailRepository): DisposableViewModel(), AreaDetailEventListener {


    //AreaDetailActivity
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick


    //InformationFragment
    private val _areaInformation = MutableLiveData<MutableList<AreaInformationResult>>()
    val areaInformation: LiveData<MutableList<AreaInformationResult>>
        get() = _areaInformation

    private val _addScheduleClick = SingleLiveEvent<Any>()
    val addScheduleClick: LiveData<Any>
        get() = _addScheduleClick


    //AccompanyFragment
    private val _accompanyList = MutableLiveData<MutableList<AccompanyListResult>>()
    val accompanyList: LiveData<MutableList<AccompanyListResult>>
        get() = _accompanyList


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

    fun bringAccompanyList() {
        areaDetailRepository.bringAccompanyList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _accompanyList.value = it.accompanylist
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAccompany(position: Int) {

    }

}

interface AreaDetailEventListener {

}