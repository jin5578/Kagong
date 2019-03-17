package com.tistory.jeongs0222.kagongapplication.ui.view.adddetailschedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AddDetailScheduleRepository
import com.tistory.jeongs0222.kagongapplication.ui.view.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddDetailScheduleViewModel(private val addDetailScheduleRepository: AddDetailScheduleRepository): DisposableViewModel(),
    AddDetailScheduleEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _moreClick = SingleLiveEvent<Any>()

    private val _editScheduleClick = SingleLiveEvent<Any>()
    val editScheduleClick: LiveData<Any>
        get() = _editScheduleClick

    private val _deleteScheduleClick = SingleLiveEvent<Any>()
    val deleteScheduleClick: LiveData<Any>
        get() = _deleteScheduleClick

    private val _cancelClick = SingleLiveEvent<Any>()

    private val _editLocationClick = SingleLiveEvent<Any>()
    val editLocationClick: LiveData<Any>
        get() = _editLocationClick

    private val _deleteLocationClick = SingleLiveEvent<Any>()
    val deleteLocationClick: LiveData<Any>
        get() = _deleteLocationClick

    private val _cancelLocationClick = SingleLiveEvent<Any>()

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
    }

    fun moreClickEvent() {
        _moreClick.call()

        _moreVisibility.value = true
    }

    fun editScheduleClickEvent() {
        _editScheduleClick.call()
    }

    fun deleteScheduleClickEvent() {
        _deleteScheduleClick.call()
    }

    fun cancelClickEvent() {
        _cancelClick.call()

        _moreVisibility.value = false
    }

    fun editLocationClickEvent() {
        _editLocationClick.call()
    }

    fun deleteLocationClickEvent() {
        _deleteLocationClick.call()
    }

    fun cancelLocationClickEvent() {
        _cancelLocationClick.call()

        _moreLocationVisibility.value = false
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