package com.tistory.jeongs0222.kagongapplication.ui.accompanyDetail

import android.os.Bundle
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAccompanyDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccompanyDetailActivity: BaseActivity<ActivityAccompanyDetailBinding>() {

    private val TAG = "AccompanyDetailActivity"

    override val layoutResourceId: Int = R.layout.activity_accompany_detail

    private val accompanyDetailViewModel by viewModel<AccompanyDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}