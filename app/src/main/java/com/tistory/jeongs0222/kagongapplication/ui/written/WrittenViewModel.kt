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

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _moreEditClick = SingleLiveEvent<Any>()
    val moreEditClick: LiveData<Any>
        get() = _moreEditClick

    private val _moreDeleteClick = SingleLiveEvent<Any>()
    val moreDeleteClick: LiveData<Any>
        get() = _moreDeleteClick

    private val _finishRequest = SingleLiveEvent<Any>()
    val finishRequest: LiveData<Any>
        get() = _finishRequest

    private val _writtenAccompanyList = MutableLiveData<MutableList<WrittenAccompanyResult>>()
    val writtenAccompanyList: LiveData<MutableList<WrittenAccompanyResult>>
        get() = _writtenAccompanyList

    private val _moreVisibility = MutableLiveData<Boolean>()
    val moreVisibility: LiveData<Boolean>
        get() = _moreVisibility

    private lateinit var selectedContent: String
    private lateinit var selectedWrittenTime: String

    private lateinit var messageProvider: MessageProvider

    private lateinit var userKey: String


    fun bind(dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider) {
        userKey = dbHelperProvider.getDBHelper().getUserKey()
        this.messageProvider = messageProvider

        writtenAccompany()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun moreEditClickEvent() {
        _moreEditClick.call()
    }

    fun moreDeleteClickEvent() {
        _moreDeleteClick.call()
    }

    fun moreCancelClickEvent() {
        _moreVisibility.value = false
    }

    fun deleteAccompany() {
        writtenRepository.deleteAccompany(userKey, selectedContent, selectedWrittenTime)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

                    _finishRequest.call()
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
                _writtenAccompanyList.value = it.writtenAccompany
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    override fun moreClickEvent(content: String, writtenTime: String) {
        _moreVisibility.value = true

        selectedContent = content
        selectedWrittenTime = writtenTime
    }
}

interface WrittenEventListener {
    fun moreClickEvent(content: String, writtenTime: String)
}