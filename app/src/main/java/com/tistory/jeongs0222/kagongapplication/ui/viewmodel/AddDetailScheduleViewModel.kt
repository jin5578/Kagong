package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.transition.Visibility
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class AddDetailScheduleViewModel: DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _moreClick = SingleLiveEvent<Any>()
    val moreClick: LiveData<Any>
        get() = _moreClick

    private val _cancelClick = SingleLiveEvent<Any>()
    val cancelClick: LiveData<Any>
        get() = _cancelClick

    private val _moreVisibility = MutableLiveData<Boolean>()
    val moreVisibility: LiveData<Boolean>
        get() = _moreVisibility

    private val TAG = "AddDetaiViewModel"


    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }

    fun moreClickEvent() {
        _moreClick.call()

        _moreVisibility.value = true

        Log.e(TAG, "moreClick")
    }

    fun cancelClickEvent() {
        _cancelClick.call()

        _moreVisibility.value = false

        Log.e(TAG, "cancelClick")
    }


}