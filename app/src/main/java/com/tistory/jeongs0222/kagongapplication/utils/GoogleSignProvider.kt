package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tistory.jeongs0222.kagongapplication.R


interface GoogleSignProvider {
    fun getGoogleSignInOptions() : GoogleSignInOptions

    fun getGoogleSignInClient(gso : GoogleSignInOptions) : GoogleSignInClient

    fun googleSignIn(googleSignInClient : GoogleSignInClient)

}

class GoogleSignProviderImpl(private val activity: Activity) : GoogleSignProvider {
    override fun getGoogleSignInOptions(): GoogleSignInOptions {
        return  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    override fun getGoogleSignInClient(gso : GoogleSignInOptions) : GoogleSignInClient {
        return GoogleSignIn.getClient(activity, gso)
    }

    override fun googleSignIn(googleSignInClient : GoogleSignInClient) {
        val signInIntent = googleSignInClient.signInIntent

        activity.startActivityForResult(signInIntent, 10)
    }

}