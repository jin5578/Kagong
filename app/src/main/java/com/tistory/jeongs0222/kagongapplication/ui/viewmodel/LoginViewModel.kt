package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.tistory.jeongs0222.kagongapplication.ui.view.activity.MainActivity
import com.tistory.jeongs0222.kagongapplication.utils.*


class LoginViewModel : DisposableViewModel() {

    private val _googleLogin = SingleLiveEvent<ConstraintLayout>()
    val googleLogin: LiveData<ConstraintLayout>
        get() = _googleLogin

    private val TAG = "LoginViewModel"

    private lateinit var googleSignProvider: GoogleSignProvider
    private lateinit var dbHelperProvider: DBHelperProvider
    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider

    private lateinit var gso: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var user: String

    fun bind(googleSignProvider: GoogleSignProvider, dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider, intentProvider: IntentProvider) {
        this.googleSignProvider = googleSignProvider
        this.dbHelperProvider = dbHelperProvider
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider

        validateUserCheck()

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

    fun firebaseSignIn(acct: GoogleSignInAccount) {
        googleSignProvider.firebaseSignIn(acct).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                user = FirebaseAuth.getInstance().currentUser!!.uid

                messageProvider.toastMessage("구글 로그인에 성공하였습니다.")


            } else {
                messageProvider.toastMessage("구글 로그인에 실패하였습니다.")
            }
        }
    }

    private fun validateUserCheck() {
        if(!dbHelperProvider.getDBHelper().getUserkey().isEmpty()) {
            intentProvider.intent(MainActivity::class.java)
        }
    }

}