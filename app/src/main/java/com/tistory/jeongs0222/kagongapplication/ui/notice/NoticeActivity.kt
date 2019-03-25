package com.tistory.jeongs0222.kagongapplication.ui.notice

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityNoticeBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.notice.fragment.NoticeDetailFragment
import com.tistory.jeongs0222.kagongapplication.ui.notice.fragment.NoticeListFragment
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoticeActivity: BaseActivity<ActivityNoticeBinding>() {

    override val layoutResourceId: Int = R.layout.activity_notice

    private val noticeViewModel by viewModel<NoticeViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val noticeListFragment = NoticeListFragment()
    private val noticeDetailFragment = NoticeDetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(noticeListFragment)

        noticeViewModel.listPreviousClick.observe(this@NoticeActivity, Observer {
            finish()
        })

        noticeViewModel.detailPreviousClick.observe(this@NoticeActivity, Observer {
            fragmentProvider.replaceFragment(noticeDetailFragment)
        })

        noticeViewModel.noticeItemClick.observe(this@NoticeActivity, Observer {
            fragmentProvider.replaceFragment(noticeDetailFragment)
        })

        viewDataBinding.nViewModel = noticeViewModel
        viewDataBinding.lifecycleOwner = this@NoticeActivity
    }

    override fun onBackPressed() {
        finish()
    }

}