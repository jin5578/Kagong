package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.tistory.jeongs0222.kagongapplication.model.repository.LoginRepository
import com.tistory.jeongs0222.kagongapplication.ui.view.activity.InAgreementActivity
import com.tistory.jeongs0222.kagongapplication.ui.view.activity.MainActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel(private val loginRepository: LoginRepository) : DisposableViewModel() {

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

                keyCheck(user)

                messageProvider.toastMessage("구글 로그인에 성공하였습니다.")


            } else {
                messageProvider.toastMessage("구글 로그인에 실패하였습니다.")
            }
        }
    }

    private fun validateUserCheck() {
        if(!dbHelperProvider.getDBHelper().getGooglekey().isEmpty()) {
            intentProvider.finishIntent(MainActivity::class.java)

            Log.e(TAG + "userCheck", true.toString())
        } else {
            Log.e(TAG + "userCheck", false.toString())
        }
    }

    @SuppressLint("CheckResult")
    private fun keyCheck(userkey: String) {
        loginRepository.keyCheck(userkey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                when {
                    it.value == 0 -> {
                        Log.e(TAG, it.value.toString())

                        intentProvider.finishIntent(InAgreementActivity::class.java)
                    }

                    it.value == 1 -> {
                        Log.e(TAG, it.value.toString())

                        intentProvider.finishIntent(InAgreementActivity::class.java)
                    }

                    it.value == 2 -> {
                        intentProvider.finishIntent(MainActivity::class.java)

                        dbHelperProvider.getDBHelper().insertUser(userkey)
                    }

                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

}