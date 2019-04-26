package com.tistory.jeongs0222.kagongapplication.ui.locationdetail

import android.os.Bundle
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLocationDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LocationDetailActivity: BaseActivity<ActivityLocationDetailBinding>() {

    override val layoutResourceId: Int = R.layout.activity_location_detail

    private val viewModel by viewModel<LocationDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.ldViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@LocationDetailActivity
    }

    override fun onBackPressed() {
        finish()
    }

}