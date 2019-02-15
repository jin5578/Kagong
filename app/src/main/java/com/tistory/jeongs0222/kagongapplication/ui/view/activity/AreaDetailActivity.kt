package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAreaDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AreaDetailActivity : BaseActivity<ActivityAreaDetailBinding>() {

    private val TAG = "AreaDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_area_detail

    private val areaDetailViewModel by viewModel<AreaDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG, "onCreate")

        viewDataBinding.dViewModel = areaDetailViewModel
        viewDataBinding.setLifecycleOwner(this@AreaDetailActivity)
    }

    override fun onBackPressed() {
        finish()
    }
}
