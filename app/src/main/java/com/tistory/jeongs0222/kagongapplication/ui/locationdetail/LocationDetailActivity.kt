package com.tistory.jeongs0222.kagongapplication.ui.locationdetail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLocationDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.googleMap.GoogleMapActivity
import com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter.LocationReviewAdapter
import com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter.OperatingTimeAdapter
import com.tistory.jeongs0222.kagongapplication.ui.review.ReviewActivity
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationDetailActivity : BaseActivity<ActivityLocationDetailBinding>() {

    private val TAG = "LocationDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_location_detail

    private val viewModel by viewModel<LocationDetailViewModel>()

    private val intentProvider = IntentProviderImpl(this@LocationDetailActivity) as IntentProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order: Int = intent.getIntExtra("value1", 0)

        viewDataBinding.operationRecycler.apply {
            layoutManager = LinearLayoutManager(this@LocationDetailActivity)
            adapter = OperatingTimeAdapter(this@LocationDetailActivity)
            isNestedScrollingEnabled = false
        }

        viewDataBinding.reviewRecycler.apply {
            layoutManager = LinearLayoutManager(this@LocationDetailActivity)
            adapter = LocationReviewAdapter(this@LocationDetailActivity)
            isNestedScrollingEnabled = false
        }

        viewModel.bind(order, DBHelperProviderImpl(this@LocationDetailActivity))

        viewModel.previousClick.observe(this@LocationDetailActivity, Observer {
            finish()
        })

        viewModel.locationClick.observe(this@LocationDetailActivity, Observer {
            intentProvider.iIntent(GoogleMapActivity::class.java, order)
        })

        viewModel.reviewWriteClick.observe(this@LocationDetailActivity, Observer {
            intentProvider.iiIntent(ReviewActivity::class.java, order, 1)      // 1은 ReviewWriteFragment
        })

        viewModel.reviewMoreClick.observe(this@LocationDetailActivity, Observer {
            intentProvider.iiIntent(ReviewActivity::class.java, order, 0)       // 0은 ReviewMoreFragment
        })


        viewDataBinding.ldViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@LocationDetailActivity
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onResume() {
        super.onResume()

        viewModel.locationLikeValidate()
    }

}