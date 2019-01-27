package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent


class LoginViewModel : DisposableViewModel() {

    private val TAG = "LoginViewModel"

    private val _googleLogin = SingleLiveEvent<ConstraintLayout>()
    val googleLogin: LiveData<ConstraintLayout> get() = _googleLogin

    fun googleLoginClick() {
        _googleLogin.call()
    }

    fun googleLogin() {
        Log.e(TAG, "googleLoginClickAndEvent")
    }

}