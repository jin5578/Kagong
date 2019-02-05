package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel: DisposableViewModel() {

    private val _test1 = MutableLiveData<String>()
    val test1: LiveData<String>
        get() = _test1

    fun test1Event(value: String) {
        _test1.value = value
    }

}