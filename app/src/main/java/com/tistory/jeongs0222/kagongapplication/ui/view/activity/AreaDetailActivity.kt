package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAreaDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailfragment.*
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import kotlinx.android.synthetic.main.activity_area_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AreaDetailActivity : BaseActivity<ActivityAreaDetailBinding>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val TAG = "AreaDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_area_detail

    private val areaDetailViewModel by viewModel<AreaDetailViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val intentProvider = IntentProviderImpl(this@AreaDetailActivity) as IntentProvider

    private val informationFragment = InformationFragment()
    private val weatherFragment = WeatherFragment()
    private val tourismFragment = TourismFragment()
    private val accompanyFragment = AccompanyFragment()

    private lateinit var area: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        Log.e(TAG, area)

        fragmentProvider.initFragment(informationFragment)

        bottomNavi.setOnNavigationItemSelectedListener(this@AreaDetailActivity)

        areaDetailViewModel.bringAreaInformation(area)

        areaDetailViewModel.previousClick.observe(this@AreaDetailActivity, Observer {
            finish()
        })

        areaDetailViewModel.addScheduleClick.observe(this@AreaDetailActivity, Observer {
            intentProvider.intentPutExtra(AddScheduleActivity::class.java,  area)
            //intentProvider.intent(AddScheduleActivity::class.java)
        })

        viewDataBinding.dViewModel = areaDetailViewModel
        viewDataBinding.lifecycleOwner = this@AreaDetailActivity
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

            R.id.accompany_menu -> {
                fragmentProvider.replaceFragment(accompanyFragment)
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
