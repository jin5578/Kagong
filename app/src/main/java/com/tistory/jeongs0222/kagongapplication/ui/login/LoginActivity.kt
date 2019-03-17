package com.tistory.jeongs0222.kagongapplication.ui.view.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.BaseActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity"

    override val layoutResourceId: Int = R.layout.activity_login

    private val loginViewModel by viewModel<LoginViewModel>()

    private val googleSignProvider = GoogleSignProviderImpl(this@LoginActivity) as GoogleSignProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@LoginActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@LoginActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@LoginActivity) as IntentProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.bind(googleSignProvider, dbHelperProvider, messageProvider, intentProvider)

        loginViewModel.googleLogin.observe(this, Observer {
            loginViewModel.googleLogin()
        })

        viewDataBinding.lViewModel = loginViewModel
        viewDataBinding.lifecycleOwner = this@LoginActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    loginViewModel.firebaseSignIn(account)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

}