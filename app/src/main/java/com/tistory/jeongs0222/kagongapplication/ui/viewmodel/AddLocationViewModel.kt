package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class AddLocationViewModel: DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _deleteClick = SingleLiveEvent<Any>()
    val deleteClick: LiveData<Any>
        get() = _deleteClick

    private val _additionClick = SingleLiveEvent<Any>()
    val additionClick: LiveData<Any>
        get() = _additionClick

    private val TAG = "AddLocationViewModel"


    fun deleteClickEvent() {
        _deleteClick.call()

        Log.e(TAG, "deleteClick")
    }

    fun additionClickEvent() {
        _additionClick.call()

        Log.e(TAG, "additionClick")
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }
}