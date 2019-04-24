package com.tistory.jeongs0222.kagongapplication.ui.written

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResult
import com.tistory.jeongs0222.kagongapplication.model.repository.WrittenRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class WrittenViewModel(private val writtenRepository: WrittenRepository): DisposableViewModel(), WrittenEventListener {

    private val _wPreviousClick = SingleLiveEvent<Any>()
    val wPreviousClick: LiveData<Any>
        get() = _wPreviousClick

    private val _wMoreDeteleClick = SingleLiveEvent<Any>()
    val _wMoreDeleteClick: LiveData<Any>
        get() = _wMoreDeteleClick

    private val _wFinishRequest = SingleLiveEvent<Any>()
    val wFinishRequest: LiveData<Any>
        get() = _wFinishRequest

    private val _wWrittenAccompanyList = MutableLiveData<MutableList<WrittenAccompanyResult>>()
    val wWrittenAccompanyList: LiveData<MutableList<WrittenAccompanyResult>>
        get() = _wWrittenAccompanyList

    private val _wMoreVisibility = MutableLiveData<Boolean>()
    val wMoreVisibility: LiveData<Boolean>
        get() = _wMoreVisibility

    private lateinit var messageProvider: MessageProvider

    private lateinit var selectedContent: String
    private lateinit var selectedWrittenTime: String

    private lateinit var userKey: String


    fun wBind(dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider) {
        userKey = dbHelperProvider.getDBHelper().getUserKey()
        this.messageProvider = messageProvider

        writtenAccompany()
    }

    fun wPreviousClickEvent() {
        _wPreviousClick.call()
    }

    fun wMoreDeleteClickEvent() {
        _wMoreDeteleClick.call()
    }

    fun wMoreCancelClickEvent() {
        _wMoreVisibility.value = false
    }

    fun wDeleteAccompany() {
        writtenRepository.deleteAccompany(userKey, selectedContent, selectedWrittenTime)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

                    _wFinishRequest.call()
                } else {
                    messageProvider.toastMessage(it.message)
                }
            }
            .doOnError {

            }
            .subscribe()
    }

    private fun writtenAccompany() {
        writtenRepository.writtenAccompany(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _wWrittenAccompanyList.value = it.writtenAccompany
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    override fun moreClickEvent(content: String, writtenTime: String) {
        _wMoreVisibility.value = true

        selectedContent = content
        selectedWrittenTime = writtenTime
    }
}

interface WrittenEventListener {
    fun moreClickEvent(content: String, writtenTime: String)
}