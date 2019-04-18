package com.tistory.jeongs0222.kagongapplication.ui.howToUse

import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class HowToUseViewModel : DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    val image1: Int = R.drawable.accompany_write
    val image2: Int = R.drawable.accompany_write1
    val image3: Int = R.drawable.accompany_write2
    val image4: Int = R.drawable.accompany_write3
    val image5: Int = R.drawable.accompany_write4

    fun previousClickEvent() {
        _previousClick.call()
    }

}