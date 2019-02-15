package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class AreaDetailViewModel: DisposableViewModel() {


    //AreaDetailActivity
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick


    private val TAG = "AreaDetailViewModel"

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }
}