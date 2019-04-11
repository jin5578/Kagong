package com.tistory.jeongs0222.kagongapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivitySplashBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResourceId: Int = R.layout.activity_splash

    private val splashViewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.openDelay.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, LoginActivity::class.java))

                finish()
            }
        })

        viewDataBinding.sViewModel = splashViewModel
        viewDataBinding.lifecycleOwner = this@SplashActivity
    }

}
