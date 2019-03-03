package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddDetailScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDetailScheduleActivity : BaseActivity<ActivityAddDetailScheduleBinding>() {

    private val TAG = "AddDetailScheduleActivity"

    override val layoutResourceId: Int = R.layout.activity_add_detail_schedule

    private val addDetailScheduleViewModel by viewModel<AddDetailScheduleViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addDetailScheduleViewModel.previousClick.observe(this@AddDetailScheduleActivity, Observer {
            finish()
        })

        addDetailScheduleViewModel.moreVisibility.observe(this@AddDetailScheduleActivity, Observer {
            if(it) {
                viewDataBinding.moreLayout.visibility = View.VISIBLE
            } else {
                viewDataBinding.moreLayout.visibility = View.GONE
            }
        })



        viewDataBinding.adViewModel = addDetailScheduleViewModel
        viewDataBinding.lifecycleOwner = this@AddDetailScheduleActivity
    }

    override fun onBackPressed() {
        finish()
    }
}
