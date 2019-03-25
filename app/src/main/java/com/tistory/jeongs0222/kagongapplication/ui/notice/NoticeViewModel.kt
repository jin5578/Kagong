package com.tistory.jeongs0222.kagongapplication.ui.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResult
import com.tistory.jeongs0222.kagongapplication.model.repository.NoticeRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NoticeViewModel(private val noticeRepository: NoticeRepository): DisposableViewModel(), NoticeEventListener {

    private val _listPreviousClick = SingleLiveEvent<Any>()
    val listPreviousClick: LiveData<Any>
        get() = _listPreviousClick

    private val _detailPreviousClick = SingleLiveEvent<Any>()
    val detailPreviousClick: LiveData<Any>
        get() = _detailPreviousClick

    private val _noticeItemClick = SingleLiveEvent<Any>()
    val noticeItemClick: LiveData<Any>
        get() = _noticeItemClick

    private val _noticeList = MutableLiveData<MutableList<BringNoticeResult>>()
    val noticeList: LiveData<MutableList<BringNoticeResult>>
        get() = _noticeList

    private val _selectedPosition = MutableLiveData<Int>()
    val selectedPosition: LiveData<Int>
        get() = _selectedPosition

    init {
        bringNotice()
    }

    private fun bringNotice() {
        noticeRepository.bringNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _noticeList.value = it.bringNotice
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun listPreviousClickEvent() {
        _listPreviousClick.call()
    }

    fun detailPreviousClickEvent() {
        _detailPreviousClick.call()
    }

    override fun noticeItemClickEvent(position: Int) {
        _selectedPosition.value = position

        _noticeItemClick.call()
    }

}

interface NoticeEventListener {

    fun noticeItemClickEvent(position: Int)

}