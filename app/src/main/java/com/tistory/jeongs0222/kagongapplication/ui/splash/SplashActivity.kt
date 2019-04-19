package com.tistory.jeongs0222.kagongapplication.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivitySplashBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.appintroduce.AppIntroduceActivity
import com.tistory.jeongs0222.kagongapplication.ui.login.LoginActivity
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResourceId: Int = R.layout.activity_splash

    private val splashViewModel by viewModel<SplashViewModel>()

    private val intentProvider = IntentProviderImpl(this@SplashActivity) as IntentProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.openDelay.observe(this, Observer {
            if (it) {
                intentProvider.finishIntent(AppIntroduceActivity::class.java)
            }
        })

        viewDataBinding.sViewModel = splashViewModel
        viewDataBinding.lifecycleOwner = this@SplashActivity
    }

}
