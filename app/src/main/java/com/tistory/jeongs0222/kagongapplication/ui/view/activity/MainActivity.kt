package com.tistory.jeongs0222.kagongapplication.ui.view.activity


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityMainBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.HomeFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.ProfileFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.SearchAreaFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.ScheduleFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity"

    override val layoutResourceId: Int = R.layout.activity_main

    private val mainViewModel by viewModel<MainViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@MainActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@MainActivity) as MessageProvider

    private val homeFragment = HomeFragment()
    private val scheduleFragment = ScheduleFragment()
    private val profileFragment = ProfileFragment()
    private val searchAreaFragment = SearchAreaFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG + "googlekey", dbHelperProvider.getDBHelper().getGooglekey())

        fragmentProvider.initFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)

        mainViewModel.bind(messageProvider)

        mainViewModel.searchAreaClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(searchAreaFragment)
        })

        mainViewModel.previousClick.observe(this@MainActivity, Observer {
            fragmentProvider.replaceFragment(homeFragment)
        })

        viewDataBinding.mViewModel = mainViewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.home_menu ->
                fragmentProvider.replaceFragment(homeFragment)

            R.id.location_menu ->
                fragmentProvider.replaceFragment(scheduleFragment)

            R.id.profile_menu ->
                fragmentProvider.replaceFragment(profileFragment)
        }

        return false
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
}
