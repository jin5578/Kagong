package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddLocationActivity: BaseActivity<ActivityAddLocationBinding>() {

    override val layoutResourceId: Int = R.layout.activity_add_location

    private val addLocationViewModel by viewModel<AddLocationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addLocationViewModel.previousClick.observe(this@AddLocationActivity, Observer {
            finish()
        })

        viewDataBinding.alViewModel = addLocationViewModel
        viewDataBinding.lifecycleOwner = this@AddLocationActivity
    }

    override fun onBackPressed() {
        finish()
    }
}