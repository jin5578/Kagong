package com.tistory.jeongs0222.kagongapplication.ui.review

import android.util.Log
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class ReviewViewModel: DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _checkClick = SingleLiveEvent<Any>()
    val checkClick: LiveData<Any>
        get() = _checkClick

    private val TAG = "ReviewViewModel"

    fun previousClickEvent() {
        Log.e(TAG, "previousClick")

        _previousClick.call()
    }

    fun checkClickEvent() {
        Log.e(TAG, "checkClick")

        _checkClick.call()
    }
}