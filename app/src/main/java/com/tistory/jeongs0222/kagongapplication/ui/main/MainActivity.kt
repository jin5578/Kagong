package com.tistory.jeongs0222.kagongapplication.ui.view.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityMainBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.view.main.fragment.HomeFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.main.fragment.ProfileFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.main.fragment.SearchAreaFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.main.fragment.ScheduleFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity"

    override val layoutResourceId: Int = R.layout.activity_main

    private val mainViewModel by viewModel<MainViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val messageProvider = MessageProviderImpl(this@MainActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@MainActivity) as IntentProvider

    private val homeFragment = HomeFragment()
    private val scheduleFragment = ScheduleFragment()
    private val profileFragment = ProfileFragment()
    private val searchAreaFragment = SearchAreaFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)

        mainViewModel.bind(messageProvider, intentProvider)

        mainViewModel.searchAreaClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(searchAreaFragment)
        })

        mainViewModel.previousClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(homeFragment)
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

    override fun onBackPressed() {
        mainViewModel.backPressed()

        mainViewModel.viewFinish.observe(this@MainActivity, Observer {
            if(it) {
                moveTaskToBack(true)
                finishAffinity()
                android.os.Process.killProcess(android.os.Process.myPid())
            }

        })
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.bringHistory()
        mainViewModel.bringSchedule()
    }
}