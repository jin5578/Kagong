package com.tistory.jeongs0222.kagongapplication.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivitySplashBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.appintroduce.AppIntroduceActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override val layoutResourceId: Int = R.layout.activity_splash

    private val splashViewModel by viewModel<SplashViewModel>()

    private val intentProvider = IntentProviderImpl(this@SplashActivity) as IntentProvider
    private val remoteControllerProvider = RemoteControllerProviderImpl() as RemoteControllerProvider
    private val packageInfoProvider = PackageInfoProviderImpl(this@SplashActivity) as PackageInfoProvider
    private val messageProvider = MessageProviderImpl(this@SplashActivity) as MessageProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.bind(remoteControllerProvider, packageInfoProvider, messageProvider)

        splashViewModel.openDelay.observe(this, Observer {
            if (it) {
                intentProvider.finishIntent(AppIntroduceActivity::class.java)
            }
        })

        viewDataBinding.sViewModel = splashViewModel
        viewDataBinding.lifecycleOwner = this@SplashActivity
    }
}
