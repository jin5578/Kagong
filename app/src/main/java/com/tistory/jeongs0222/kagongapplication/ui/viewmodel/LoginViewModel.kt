package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tistory.jeongs0222.kagongapplication.utils.GoogleSignProvider


class LoginViewModel : DisposableViewModel() {

    private val _googleLogin = SingleLiveEvent<ConstraintLayout>()
    val googleLogin: LiveData<ConstraintLayout>
        get() = _googleLogin

    private val TAG = "LoginViewModel"

    private lateinit var googleSignProvider: GoogleSignProvider

    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    fun bind(googleSignProvider: GoogleSignProvider) {
        this.googleSignProvider = googleSignProvider

        gso = googleSignProvider.getGoogleSignInOptions()

        mGoogleSignInClient = googleSignProvider.getGoogleSignInClient(gso)
    }

    fun googleLoginClick() {
        _googleLogin.call()
    }

    fun googleLogin() {
        googleSignProvider.googleSignIn(mGoogleSignInClient)

        Log.e(TAG, "googleLoginClickAndEvent")
    }

}