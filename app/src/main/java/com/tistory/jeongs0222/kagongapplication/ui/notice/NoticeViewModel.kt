package com.tistory.jeongs0222.kagongapplication.ui.notice

import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class NoticeViewModel: DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    fun previousClickEvent() {
        _previousClick.call()
    }

}