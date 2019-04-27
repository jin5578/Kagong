package com.tistory.jeongs0222.kagongapplication.ui.review

import android.os.Bundle
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityReviewBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReviewActivity : BaseActivity<ActivityReviewBinding>() {

    override val layoutResourceId: Int = R.layout.activity_review

    private val viewModel by viewModel<ReviewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = this@ReviewActivity
    }

}