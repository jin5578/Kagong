package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAreaDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.AreaInformationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class AreaDetailActivity : BaseActivity<ActivityAreaDetailBinding>(), TabLayout.OnTabSelectedListener {

    private val TAG = "AreaDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_area_detail

    private val areaDetailViewModel by viewModel<AreaDetailViewModel>()

    private val intentProvider = IntentProviderImpl(this@AreaDetailActivity) as IntentProvider

    private lateinit var area: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        Log.e(TAG, area)

        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AreaDetailActivity)
            adapter = AreaInformationAdapter(this@AreaDetailActivity)
        }

        viewDataBinding.tabLayout.addOnTabSelectedListener(this@AreaDetailActivity)

        areaDetailViewModel.bringAreaInformation(area)

        areaDetailViewModel.previousClick.observe(this@AreaDetailActivity, Observer {
            finish()
        })

        viewDataBinding.dViewModel = areaDetailViewModel
        viewDataBinding.lifecycleOwner = this@AreaDetailActivity
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        when(p0!!.position) {
            0 -> {
                //좋아요
            }

            1 -> intentProvider.intentPutExtra(AddScheduleActivity::class.java, area)       //일정

            2 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "1")     //관광지

            3 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "2")     //맛집

            4 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "3")     //동행

        }

    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
        when(p0!!.position) {
            0 -> {
                //좋아요
            }

            1 -> intentProvider.intentPutExtra(AddScheduleActivity::class.java, area)    //일정

            2 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "1")     //관광지

            3 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "2")     //맛집

            4 -> intentProvider.intentPutTwoExtra(AreaDetailTabActivity::class.java, area, "3")     //동행
        }
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onResume() {
        super.onResume()

        areaDetailViewModel.validateSchedule(area)
    }

    override fun onBackPressed() {
        finish()
    }
}
