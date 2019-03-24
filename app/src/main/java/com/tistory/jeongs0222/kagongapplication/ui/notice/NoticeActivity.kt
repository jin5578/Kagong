package com.tistory.jeongs0222.kagongapplication.ui.notice

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityNoticeBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoticeActivity: BaseActivity<ActivityNoticeBinding>() {

    override val layoutResourceId: Int = R.layout.activity_notice

    private val noticeViewModel by viewModel<NoticeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noticeViewModel.previousClick.observe(this@NoticeActivity, Observer {
            finish()
        })

        viewDataBinding.nViewModel = noticeViewModel
        viewDataBinding.lifecycleOwner = this@NoticeActivity
    }

    override fun onBackPressed() {
        finish()
    }

}