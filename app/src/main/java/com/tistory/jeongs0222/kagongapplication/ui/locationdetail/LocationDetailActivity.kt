package com.tistory.jeongs0222.kagongapplication.ui.locationdetail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLocationDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationDetailActivity : BaseActivity<ActivityLocationDetailBinding>() {

    private val TAG = "LocationDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_location_detail

    private val viewModel by viewModel<LocationDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order: Int = intent.getIntExtra("value1", 0)

        viewModel.bind(order, DBHelperProviderImpl(this@LocationDetailActivity))

        viewModel.previousClick.observe(this@LocationDetailActivity, Observer {
            finish()
        })

        viewModel.likeStatus.observe(this@LocationDetailActivity, Observer {
            if(it == 0) {
                viewDataBinding.like.setImageDrawable(getDrawable(R.drawable.dislike))
            } else if(it == 1) {
                viewDataBinding.like.setImageDrawable(getDrawable(R.drawable.like))
            }
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