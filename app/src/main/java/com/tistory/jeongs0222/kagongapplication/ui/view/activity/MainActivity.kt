package com.tistory.jeongs0222.kagongapplication.ui.view.activity


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityMainBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProviderImpl
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity"

    override val layoutResourceId: Int = R.layout.activity_main

    private val mainViewModel by viewModel<MainViewModel>()

    private val dbHelperProvider = DBHelperProviderImpl(this) as DBHelperProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG + "googlekey", dbHelperProvider.getDBHelper().getGooglekey())

        bottomNavigation.setOnNavigationItemSelectedListener(this@MainActivity)

        viewDataBinding.mViewModel = mainViewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.home_menu ->
                mainViewModel.test1Event("home_menu")

            R.id.location_menu ->
                mainViewModel.test1Event("location_menu")

            R.id.profile_menu ->
                mainViewModel.test1Event("profile_menu")
        }

        return false
    }
}
