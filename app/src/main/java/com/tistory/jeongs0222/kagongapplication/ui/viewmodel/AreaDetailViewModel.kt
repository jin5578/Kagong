package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailRepository
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailViewModel(private val areaDetailRepository: AreaDetailRepository): DisposableViewModel() {


    //AreaDetailActivity
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick


    //InformationFragment
    private val _areaInformation = MutableLiveData<MutableList<AreaInformationResult>>()
    val areaInformation: LiveData<MutableList<AreaInformationResult>>
        get() = _areaInformation


    private val TAG = "AreaDetailViewModel"


    init {
        bringAreaInformation()
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }

    private fun bringAreaInformation() {
        areaDetailRepository.bringAreaDetail("런던")
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

}