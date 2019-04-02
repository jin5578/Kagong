package com.tistory.jeongs0222.kagongapplication.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityMainBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.main.fragment.*
import com.tistory.jeongs0222.kagongapplication.ui.notice.NoticeActivity
import com.tistory.jeongs0222.kagongapplication.ui.profile.ProfileActivity
import com.tistory.jeongs0222.kagongapplication.ui.setting.SettingActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest


class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity"

    override val layoutResourceId: Int = R.layout.activity_main

    private val mainViewModel by viewModel<MainViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val messageProvider = MessageProviderImpl(this@MainActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@MainActivity) as IntentProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@MainActivity) as DBHelperProvider

    private val homeFragment = HomeFragment()
    private val scheduleFragment = ScheduleFragment()
    private val profileFragment = ProfileFragment()
    private val searchAreaFragment = SearchAreaFragment()

    private val checkList =
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private val PERMISSION = 111;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionCheck()

        fragmentProvider.initFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)

        mainViewModel.bind(messageProvider, intentProvider, dbHelperProvider)

        mainViewModel.searchAreaClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(searchAreaFragment)
        })

        mainViewModel.previousClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(homeFragment)
        })

        mainViewModel.profileDetailClick.observe(this@MainActivity, Observer {
            intentProvider.intent(ProfileActivity::class.java)
        })

        mainViewModel.settingClick.observe(this@MainActivity, Observer {
            intentProvider.intent(SettingActivity::class.java)
        })

        mainViewModel.noticeClick.observe(this@MainActivity, Observer {
            intentProvider.intent(NoticeActivity::class.java)
        })

        viewDataBinding.mViewModel = mainViewModel
        viewDataBinding.lifecycleOwner = this@MainActivity
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.home_menu -> {
                fragmentProvider.replaceFragment(homeFragment)

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

    private fun permissionCheck() {
        if ((ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
            && (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, checkList, PERMISSION)
            }
        }
    }

    override fun onBackPressed() {
        mainViewModel.backPressed()

        mainViewModel.viewFinish.observe(this@MainActivity, Observer {
            if (it) {
                moveTaskToBack(true)
                finishAffinity()
                android.os.Process.killProcess(android.os.Process.myPid())
            }

        })
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.bringNicknameAndIntro()
        mainViewModel.bringHistory()
        mainViewModel.bringSchedule()
    }
}
