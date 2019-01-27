package com.tistory.jeongs0222.kagongapplication.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.LoginViewModel
import com.tistory.jeongs0222.kagongapplication.utils.GoogleSignProvider
import com.tistory.jeongs0222.kagongapplication.utils.GoogleSignProviderImpl

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity"

    override val layoutResourceId: Int = R.layout.activity_login

    private val googleSignProvider = GoogleSignProviderImpl(this) as GoogleSignProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel = LoginViewModel()

        loginViewModel.bind(googleSignProvider)

        loginViewModel.googleLogin.observe(this, Observer {
            loginViewModel.googleLogin()
        })

        viewDataBinding.lViewModel = loginViewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e(TAG, "1")

        if(requestCode == 10) {
            Log.e(TAG, "2")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                Log.e(TAG, "3")
                val account = task.getResult(ApiException::class.java)

                Log.e(TAG, account.toString())

            } catch (e: ApiException) {
                Log.e(TAG, "4")

                e.printStackTrace()
            }
            /*Log.e(TAG, "2")

            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            if(result.isSuccess) {
                Log.e(TAG, "3")

                val account = result.signInAccount

                Log.e(TAG, account.toString())
            } else {
                Log.e(TAG, "4")

            }*/
        }
    }
}
