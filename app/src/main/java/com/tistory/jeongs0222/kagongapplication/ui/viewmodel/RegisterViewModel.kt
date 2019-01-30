package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.AgeFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.PersonalInfoFragment
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class RegisterViewModel: DisposableViewModel() {

    private val _nextClick = SingleLiveEvent<Any>()
    val nextClick: LiveData<Any> get() = _nextClick

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any> get() = _previousClick

    private val _femaleClick = SingleLiveEvent<Any>()
    val femaleClick: LiveData<Any> get() = _femaleClick

    private val _maleClick = SingleLiveEvent<Any>()
    val maleClick: LiveData<Any> get() = _maleClick

    private val _ageClick = SingleLiveEvent<Any>()
    val ageClick: LiveData<Any> get() = _ageClick

    private val _userSex = MutableLiveData<String>()
    val userSex: LiveData<String> get() = _userSex

    private val TAG = "RegisterViewModel"


    init {
        Log.e(TAG, "RegisterViewModelCreated")
    }



    fun nextClickEvent() {
        _nextClick.call()

        Log.e(TAG, "test1Click")
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "test2Click")
    }

    fun femaleClickEvent() {
        _femaleClick.call()

        _userSex.value = "female"
    }

    fun maleClickEvent() {
        _maleClick.call()

        _userSex.value = "male"
    }

    fun ageClickEvent() {
        _ageClick.call()
    }

}