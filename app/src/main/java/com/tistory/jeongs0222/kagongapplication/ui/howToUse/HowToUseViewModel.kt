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

    val image6: Int = R.drawable.schedulehtu1
    val image7: Int = R.drawable.schedulehtu2
    val image8: Int = R.drawable.schedulehtu3
    val image9: Int = R.drawable.schedulehtu4
    val image10: Int = R.drawable.schedulehtu5
    val image11: Int = R.drawable.schedulehtu6

    fun previousClickEvent() {
        _previousClick.call()
    }

}