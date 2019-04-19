package com.tistory.jeongs0222.kagongapplication.ui.appintroduce

import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class AppIntroduceViewModel: DisposableViewModel(), AppIntroduceEventListener {

    private val _startClick = SingleLiveEvent<Any>()
    val startClick: LiveData<Any>
        get() = _startClick

    override fun startClickEvent() {
        _startClick.call()
    }

}

interface AppIntroduceEventListener {
    fun startClickEvent()
}