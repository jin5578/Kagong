package com.tistory.jeongs0222.kagongapplication.ui.view

import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import android.os.Bundle
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val layoutResourceId: Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel = LoginViewModel()

        viewDataBinding.viewmodel = loginViewModel
        viewDataBinding.setLifecycleOwner(this)

    }
}
