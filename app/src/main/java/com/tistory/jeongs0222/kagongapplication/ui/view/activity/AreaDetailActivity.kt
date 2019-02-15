package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAreaDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailfragment.InformationFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailfragment.TourismFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailfragment.WeatherFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import kotlinx.android.synthetic.main.activity_area_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AreaDetailActivity : BaseActivity<ActivityAreaDetailBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "AreaDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_area_detail

    private val areaDetailViewModel by viewModel<AreaDetailViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val informationFragment = InformationFragment()
    private val weatherFragment = WeatherFragment()
    private val tourismFragment = TourismFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(informationFragment)

        bottomNavi.setOnNavigationItemSelectedListener(this@AreaDetailActivity)

        areaDetailViewModel.previousClick.observe(this@AreaDetailActivity, Observer {
            finish()
        })

        viewDataBinding.dViewModel = areaDetailViewModel
        viewDataBinding.setLifecycleOwner(this@AreaDetailActivity)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.info_menu -> {
                fragmentProvider.replaceFragment(informationFragment)
                true
            }

            R.id.weather_menu -> {
                fragmentProvider.replaceFragment(weatherFragment)
                true
            }

            R.id.toursim_menu -> {
                fragmentProvider.replaceFragment(tourismFragment)
                true
            }

            else ->
                false
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
