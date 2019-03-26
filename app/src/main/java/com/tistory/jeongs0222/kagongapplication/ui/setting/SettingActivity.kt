package com.tistory.jeongs0222.kagongapplication.ui.setting

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivitySettingBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.setting.fragment.SettingHomeFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    override val layoutResourceId: Int = R.layout.activity_setting

    private val settingViewModel by viewModel<SettingViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@SettingActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@SettingActivity) as MessageProvider

    private val settingHomeFragment = SettingHomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(settingHomeFragment)

        settingViewModel.bind(dbHelperProvider, messageProvider)

        settingViewModel.homePreviousClick.observe(this@SettingActivity, Observer {
            finish()
        })

        settingViewModel.appShutDownRequest.observe(this@SettingActivity, Observer {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        })

        viewDataBinding.sViewModel = settingViewModel
        viewDataBinding.lifecycleOwner = this@SettingActivity
    }

    override fun onBackPressed() {
        finish()
    }

}