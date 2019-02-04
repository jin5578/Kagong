package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.LoginViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.internal.schedulers.RxThreadFactory


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity"

    override val layoutResourceId: Int = R.layout.activity_login

    private lateinit var loginViewModel: LoginViewModel

    private val googleSignProvider = GoogleSignProviderImpl(this@LoginActivity) as GoogleSignProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@LoginActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@LoginActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@LoginActivity) as IntentProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = LoginViewModel()

        loginViewModel.bind(googleSignProvider, dbHelperProvider, messageProvider, intentProvider)

        loginViewModel.googleLogin.observe(this, Observer {
            loginViewModel.googleLogin()
        })

        viewDataBinding.lViewModel = loginViewModel
        viewDataBinding.setLifecycleOwner(this)
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
