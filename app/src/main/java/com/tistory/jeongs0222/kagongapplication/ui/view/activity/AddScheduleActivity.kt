package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddScheduleActivity : BaseActivity<ActivityAddScheduleBinding>(){

    override val layoutResourceId: Int = R.layout.activity_add_schedule

    private val addScheduleViewModel by viewModel<AddScheduleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.aViewModel = addScheduleViewModel
        viewDataBinding.setLifecycleOwner(this@AddScheduleActivity)
    }

}
