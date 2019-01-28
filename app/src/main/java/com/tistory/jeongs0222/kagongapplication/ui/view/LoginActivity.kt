package com.tistory.jeongs0222.kagongapplication.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
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

        if(requestCode == 10) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    googleSignProvider.firebaseSignIn(account)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

}
