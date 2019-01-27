package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class LoginViewModel : DisposableViewModel() {

    private val TAG = "LoginViewModel"

    private val _googleLoginBtn = SingleLiveEvent<Any>()
    val googleLoginBtn: LiveData<Any> get() = _googleLoginBtn

    fun googleLoginClickEvent() {
        _googleLoginBtn.call()

        Log.e(TAG, "googleLoginEvent")
    }

    fun googleLogin() {

    }
}