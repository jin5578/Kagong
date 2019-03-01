package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivitySplashBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.SplashViewModel


class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResourceId: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel = SplashViewModel()

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
