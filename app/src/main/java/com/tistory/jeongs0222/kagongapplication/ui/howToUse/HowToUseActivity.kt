package com.tistory.jeongs0222.kagongapplication.ui.howToUse

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityHowToUseBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.howToUse.fragment.AccompanyHTUFragment
import com.tistory.jeongs0222.kagongapplication.ui.howToUse.fragment.ScheduleHTUFragment
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class HowToUseActivity: BaseActivity<ActivityHowToUseBinding>() {

    override val layoutResourceId: Int = R.layout.activity_how_to_use

    private val howToUseViewModel by viewModel<HowToUseViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sort = intent.getIntExtra("value1", 0)

        when(sort) {
            0 -> fragmentProvider.initFragment(AccompanyHTUFragment())

            else -> fragmentProvider.initFragment(ScheduleHTUFragment())
        }

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