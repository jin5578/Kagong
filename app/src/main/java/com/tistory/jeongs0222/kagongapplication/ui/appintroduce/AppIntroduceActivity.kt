package com.tistory.jeongs0222.kagongapplication.ui.appintroduce

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAppIntroduceBinding
import com.tistory.jeongs0222.kagongapplication.ui.appintroduce.adapter.PictureViewPagerAdapter
import com.tistory.jeongs0222.kagongapplication.ui.login.LoginActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AppIntroduceActivity: BaseActivity<ActivityAppIntroduceBinding>() {

    override val layoutResourceId: Int = R.layout.activity_app_introduce

    private val appIntroduceViewModel by viewModel<AppIntroduceViewModel>()

    private val intentProvider = IntentProviderImpl(this@AppIntroduceActivity) as IntentProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@AppIntroduceActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(dbHelperProvider.getDBHelper().getUserKey().isNotEmpty()) {
            intentProvider.finishIntent(LoginActivity::class.java)
        }

        viewDataBinding.viewPager.apply {
            adapter = PictureViewPagerAdapter(this@AppIntroduceActivity, appIntroduceViewModel)
        }

        appIntroduceViewModel.startClick.observe(this@AppIntroduceActivity, Observer {
            intentProvider.finishIntent(LoginActivity::class.java)
        })

        viewDataBinding.dotsIndicator.setViewPager(viewDataBinding.viewPager)

        viewDataBinding.aiViewModel = appIntroduceViewModel
        viewDataBinding.lifecycleOwner = this@AppIntroduceActivity
    }


    override fun onBackPressed() {
        this@AppIntroduceActivity.apply {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}