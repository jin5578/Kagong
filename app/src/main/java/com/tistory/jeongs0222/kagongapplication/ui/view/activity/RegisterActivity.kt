package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.activity.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import com.tistory.jeongs0222.kagongapplication.utils.ViewPagerProvider
import com.tistory.jeongs0222.kagongapplication.utils.ViewPagerProviderImpl

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    private val viewPagerProvider = ViewPagerProviderImpl(supportFragmentManager) as ViewPagerProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val registerViewModel = RegisterViewModel()

        registerViewModel.bind(viewPagerProvider)

        viewDataBinding.rViewModel = registerViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
