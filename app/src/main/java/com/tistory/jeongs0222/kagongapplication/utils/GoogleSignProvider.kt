package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tistory.jeongs0222.kagongapplication.R


interface GoogleSignProvider {
    fun getGoogleSignInOptions(): GoogleSignInOptions

    fun getGoogleSignInClient(gso: GoogleSignInOptions): GoogleSignInClient

    fun googleSignIn(googleSignInClient: GoogleSignInClient)

    fun firebaseSignIn(acct: GoogleSignInAccount): Task<AuthResult>
}

class GoogleSignProviderImpl(private val activity: Activity) : GoogleSignProvider {

    private lateinit var mAuth: FirebaseAuth

    override fun getGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    override fun getGoogleSignInClient(gso: GoogleSignInOptions): GoogleSignInClient {
        return GoogleSignIn.getClient(activity, gso)
    }

    override fun googleSignIn(googleSignInClient: GoogleSignInClient) {
        val signInIntent = googleSignInClient.signInIntent

        activity.startActivityForResult(signInIntent, 10)
    }

    override fun firebaseSignIn(acct: GoogleSignInAccount): Task<AuthResult>{
        mAuth = FirebaseAuth.getInstance()

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        return mAuth.signInWithCredential(credential).addOnCompleteListener(activity) {}
    }
}