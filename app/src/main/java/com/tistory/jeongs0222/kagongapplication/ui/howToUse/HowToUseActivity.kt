package com.tistory.jeongs0222.kagongapplication.ui.howToUse

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityHowToUseBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HowToUseActivity: BaseActivity<ActivityHowToUseBinding>() {

    override val layoutResourceId: Int = R.layout.activity_how_to_use

    private val howToUseViewModel by viewModel<HowToUseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        howToUseViewModel.previousClick.observe(this@HowToUseActivity, Observer {
            finish()
        })

        viewDataBinding.hViewModel = howToUseViewModel
        viewDataBinding.lifecycleOwner = this@HowToUseActivity
    }

    override fun onBackPressed() {
        finish()
    }

}