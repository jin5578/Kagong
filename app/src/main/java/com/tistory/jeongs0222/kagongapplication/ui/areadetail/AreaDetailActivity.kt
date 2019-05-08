package com.tistory.jeongs0222.kagongapplication.ui.areadetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAreaDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter.AreaInformationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.addschedule.AddScheduleActivity
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabActivity
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
//import com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter.AccuWeatherAdapter
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AreaDetailActivity : BaseActivity<ActivityAreaDetailBinding>(), TabLayout.OnTabSelectedListener {

    private val TAG = "AreaDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_area_detail

    private val viewModel by viewModel<AreaDetailViewModel>()

    private val intentProvider = IntentProviderImpl(this@AreaDetailActivity) as IntentProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@AreaDetailActivity) as DBHelperProvider
    //private val constraintSetProvider = ConstraintSetProviderImpl(this@AreaDetailActivity) as ConstraintSetProvider

    private lateinit var area: String

    //private var weatherVisible = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        /*viewDataBinding.weatherMore.accuweatherRecycler.apply {
            layoutManager = LinearLayoutManager(this@AreaDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = AccuWeatherAdapter(
                this@AreaDetailActivity
            )
        }*/

        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AreaDetailActivity)
            adapter = AreaInformationAdapter(this@AreaDetailActivity)
        }

        viewDataBinding.tabLayout.addOnTabSelectedListener(this@AreaDetailActivity)

        viewModel.bind(area, dbHelperProvider)

        viewModel.bringAreaInformation()

        viewModel.previousClick.observe(this@AreaDetailActivity, Observer {
            finish()
        })

        viewModel.addScheduleClick.observe(this@AreaDetailActivity, Observer {
            intentProvider.intentPutExtra(AddScheduleActivity::class.java, area)    //일정
        })

        viewModel.likeStatus.observe(this@AreaDetailActivity, Observer {
            if(it == 0) {
                viewDataBinding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.dislike)
            } else if(it == 1) {
                viewDataBinding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.like)
            }
        })

        viewDataBinding.dViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@AreaDetailActivity
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        tabSelected(p0!!.position)
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
        tabSelected(p0!!.position)
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onResume() {
        super.onResume()

        viewModel.validateSchedule()

        viewModel.areaLikeValidate()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun tabSelected(position: Int) {
        when(position) {
            0 -> viewModel.areaLikeClick(viewModel.likeStatus.value!!)

            /*1 -> {
                if(weatherVisible) {
                    constraintSetProvider.moreExpandAnimation(R.layout.layout_accuweather_expand, viewDataBinding.weatherMore.moreLayout)
                    weatherVisible = false
                } else {
                    constraintSetProvider.moreExpandAnimation(R.layout.layout_accuweather_contract, viewDataBinding.weatherMore.moreLayout)
                    weatherVisible = true
                }
            }*/

            1 -> intentProvider.intentPutExtra(AddScheduleActivity::class.java, area)    //일정

            2 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "1")     //관광지

            3 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "2")     //맛집

            4 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "3")     //동행
        }
    }
}
