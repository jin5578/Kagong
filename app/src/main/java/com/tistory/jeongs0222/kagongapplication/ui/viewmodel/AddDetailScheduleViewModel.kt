package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AddDetailScheduleRepository
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddDetailScheduleViewModel(private val addDetailScheduleRepository: AddDetailScheduleRepository): DisposableViewModel(), AddDetailScheduleEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _moreClick = SingleLiveEvent<Any>()
    val moreClick: LiveData<Any>
        get() = _moreClick


    private val _editScheduleClick = SingleLiveEvent<Any>()
    val editScheduleClick: LiveData<Any>
        get() = _editScheduleClick

    private val _deleteScheduleClick = SingleLiveEvent<Any>()
    val deleteScheduleClick: LiveData<Any>
        get() = _deleteScheduleClick

    private val _cancelClick = SingleLiveEvent<Any>()
    val cancelClick: LiveData<Any>
        get() = _cancelClick


    private val _editLocationClick = SingleLiveEvent<Any>()
    val editLocationClick: LiveData<Any>
        get() = _editLocationClick

    private val _deleteLocationClick = SingleLiveEvent<Any>()
    val deleteLocationClick: LiveData<Any>
        get() = _deleteLocationClick

    private val _cancelLocationClick = SingleLiveEvent<Any>()
    val cancelLocationClick: LiveData<Any>
        get() = _cancelLocationClick


    private val _detailSchedule = MutableLiveData<MutableList<BringDetailScheduleResult>>()
    val detailSchedule: LiveData<MutableList<BringDetailScheduleResult>>
        get() = _detailSchedule


    private val _moreVisibility = MutableLiveData<Boolean>()
    val moreVisibility: LiveData<Boolean>
        get() = _moreVisibility

    private val _moreLocationVisibility = MutableLiveData<Boolean>()
    val moreLocationVisibility: LiveData<Boolean>
        get() = _moreLocationVisibility

    private val _selectedOrder = MutableLiveData<String>()
    val selectedOrder: LiveData<String>
        get() = _selectedOrder

    private val TAG = "AddDetaiViewModel"

    private lateinit var intentProvider: IntentProvider
    private lateinit var messageProvider: MessageProvider

    init {
        _selectedOrder.value = "0"
    }


    fun bind(intentProvider: IntentProvider, messageProvider: MessageProvider) {
        this.intentProvider = intentProvider
        this.messageProvider = messageProvider
    }

    fun bringDetailSchedule(area: String) {
        addDetailScheduleRepository.bringDetailSchedule(uid, area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _detailSchedule.value = it.bringdetailschedule
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun deleteSchedule(area: String) {
        Log.e("delete", "delete")

        addDetailScheduleRepository.deleteSchedule(uid, area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

                    intentProvider.intentFinish()
                } else {
                    messageProvider.toastMessage(it.message)
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun deleteLocation(area: String, position: String) {
        addDetailScheduleRepository.deleteLocation(uid, area, position)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

                    intentProvider.intentFinish()
                } else {
                    messageProvider.toastMessage(it.message)
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }


    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }

    fun moreClickEvent() {
        _moreClick.call()

        _moreVisibility.value = true

        Log.e(TAG, "moreClick")
    }

    fun editScheduleClickEvent() {
        _editScheduleClick.call()

        Log.e(TAG, "editScheduleClick")
    }

    fun deleteScheduleClickEvent() {
        _deleteScheduleClick.call()

        Log.e(TAG, "deleteScheduleClickEvent")
    }

    fun cancelClickEvent() {
        _cancelClick.call()

        _moreVisibility.value = false

        Log.e(TAG, "cancelClick")
    }

    fun editLocationClickEvent() {
        _editLocationClick.call()

        Log.e(TAG, "editLocationClick")
    }

    fun deleteLocationClickEvent() {
        _deleteLocationClick.call()

        Log.e(TAG, "deleteLocationClick")
    }

    fun cancelLocationClickEvent() {
        _cancelLocationClick.call()

        _moreLocationVisibility.value = false

        Log.e(TAG, "cancelLocationClick")
    }

    override fun locationMoreClickEvent(order: String) {
        Log.e("order", order)
        _moreLocationVisibility.value = true
        _selectedOrder.value = order
    }
}

interface AddDetailScheduleEventListener {

    fun locationMoreClickEvent(order: String)

}