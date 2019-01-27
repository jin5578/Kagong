package com.tistory.jeongs0222.kagongapplication.ui.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val layoutResourceId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel = LoginViewModel()

        loginViewModel.googleLogin.observe(this, Observer {
            loginViewModel.googleLogin()
        })

        viewDataBinding.lViewModel = loginViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
