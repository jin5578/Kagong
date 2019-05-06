package com.tistory.jeongs0222.kagongapplication.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityMainBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.AccompanyWriteActivity
import com.tistory.jeongs0222.kagongapplication.ui.main.fragment.*
import com.tistory.jeongs0222.kagongapplication.ui.notice.NoticeActivity
import com.tistory.jeongs0222.kagongapplication.ui.profile.ProfileActivity
import com.tistory.jeongs0222.kagongapplication.ui.setting.SettingActivity
import com.tistory.jeongs0222.kagongapplication.ui.userprofile.UserProfileActivity
import com.tistory.jeongs0222.kagongapplication.ui.written.WrittenActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity"

    override val layoutResourceId: Int = R.layout.activity_main

    private val viewModel by viewModel<MainViewModel>()

    private val permissionProvider = PermissionProviderImpl(this@MainActivity) as PermissionProvider
    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val intentProvider = IntentProviderImpl(this@MainActivity) as IntentProvider

    private val homeFragment = HomeFragment()
    private val searchAreaFragment = SearchAreaFragment()
    private val mainAccompanyFragment = MainAccompanyFragment()
    private val scheduleFragment = ScheduleFragment()
    private val profileFragment = ProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionProvider.permissionCheck()

        fragmentProvider.initFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)

        viewModel.bind(
            MessageProviderImpl(this@MainActivity) as MessageProvider,
            intentProvider,
            DBHelperProviderImpl(this@MainActivity) as DBHelperProvider
        )

        viewModel.searchAreaClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(searchAreaFragment)
        })

        viewModel.accompanyWriteClick.observe(this@MainActivity, Observer {
            intentProvider.intentPutExtra(AccompanyWriteActivity::class.java, viewModel.accompanyArea.value!!)
        })

        viewModel.userProfile.observe(this@MainActivity, Observer {
            intentProvider.sIntent(UserProfileActivity::class.java, it)
        })

        viewModel.previousClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(homeFragment)
        })

        viewModel.profileDetailClick.observe(this@MainActivity, Observer {
            intentProvider.intent(ProfileActivity::class.java)
        })

        viewModel.settingClick.observe(this@MainActivity, Observer {
            intentProvider.intent(SettingActivity::class.java)
        })

        viewModel.noticeClick.observe(this@MainActivity, Observer {
            intentProvider.intent(NoticeActivity::class.java)
        })

        viewModel.writtenClick.observe(this@MainActivity, Observer {
            intentProvider.intent(WrittenActivity::class.java)
        })

        viewDataBinding.mViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@MainActivity
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.home_menu -> {
                fragmentProvider.replaceFragment(homeFragment)

                true
            }

            R.id.accompany_menu -> {
                fragmentProvider.replaceFragment(mainAccompanyFragment)

                true
            }

            R.id.location_menu -> {
                fragmentProvider.replaceFragment(scheduleFragment)

                true
            }

            R.id.profile_menu -> {
                fragmentProvider.replaceFragment(profileFragment)

                true
            }

            else -> {
                false
            }
        }
    }

    override fun onBackPressed() {
        viewModel.backPressed()

        viewModel.viewFinish.observe(this@MainActivity, Observer {
            if (it) {
                moveTaskToBack(true)
                finishAffinity()
                android.os.Process.killProcess(android.os.Process.myPid())
            }

        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.bringNicknameAndIntro()
        viewModel.bringHistory()
        viewModel.bringSchedule()
    }
}
