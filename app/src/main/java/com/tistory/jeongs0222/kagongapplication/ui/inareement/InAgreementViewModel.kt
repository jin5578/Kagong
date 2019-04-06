package com.tistory.jeongs0222.kagongapplication.ui.inareement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class InAgreementViewModel : DisposableViewModel() {

    private val _agreement = MutableLiveData<Boolean>()
    val agreement: LiveData<Boolean>
        get() = _agreement

    private val _entireClick = SingleLiveEvent<Any>()

    private val _privacyPolicyClick = SingleLiveEvent<Any>()
    val privacyPolicyClick: LiveData<Any>
        get() = _privacyPolicyClick

    private val _nextClick = SingleLiveEvent<Any>()
    val nextClick: LiveData<Any>
        get() = _nextClick

    private val _closeClick = SingleLiveEvent<Any>()
    val closeClick: LiveData<Any>
        get() = _closeClick

    private val TAG = "InAgreementViewModel"


    init {
        _agreement.value = false
    }

    fun entireClickEvent() {
        _entireClick.call()

        _agreement.value = !(_agreement.value)!!
    }

    fun privacyPolicyClickEvent() {
        _privacyPolicyClick.call()
    }

    fun nextClickEvent() {
        _nextClick.call()
    }

    fun closeClickEvent() {
        _closeClick.call()
    }
}