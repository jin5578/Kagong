package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.AccompanyWriteRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AccompanyWriteViewModel(private val accompanyWriteRepository: AccompanyWriteRepository): DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _confirmClick = SingleLiveEvent<Any>()
    val confirmClick: LiveData<Any>
        get() = _confirmClick

    private val _userNickName = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickName

    private lateinit var area: String

    fun bind(area: String) {
        this.area = area
    }

    fun bringNickname() {
        accompanyWriteRepository.bringNickname(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userNickName.value = it.nickname
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e("previous Click", "previous Click")
    }

    fun confirmClickEvent() {
        _confirmClick.call()
    }
}