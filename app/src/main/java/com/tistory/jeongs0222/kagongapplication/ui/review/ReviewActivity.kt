package com.tistory.jeongs0222.kagongapplication.ui.review

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityReviewBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.review.fragment.ReviewMoreFragment
import com.tistory.jeongs0222.kagongapplication.ui.review.fragment.ReviewWriteFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReviewActivity : BaseActivity<ActivityReviewBinding>() {

    override val layoutResourceId: Int = R.layout.activity_review

    private val viewModel by viewModel<ReviewViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val reviewWriteFragment = ReviewWriteFragment()
    private val reviewMoreFragment = ReviewMoreFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order: Int
        val sort: Int

        intent.apply {
            order = intent.getIntExtra("value1", 0)
            sort = intent.getIntExtra("value2", 0)
        }

        if(sort == 0) {
            fragmentProvider.initFragment(reviewMoreFragment)
        } else {
            fragmentProvider.initFragment(reviewWriteFragment)
        }

        viewModel.bind(order, DBHelperProviderImpl(this@ReviewActivity), MessageProviderImpl(this@ReviewActivity))

        viewModel.previousClick.observe(this@ReviewActivity, Observer {
            finish()
        })

        viewModel.viewFinishRequest.observe(this@ReviewActivity, Observer {
            finish()
        })

        viewDataBinding.rViewModel = this.viewModel

        viewDataBinding.lifecycleOwner = this@ReviewActivity
    }

    override fun onBackPressed() {
        finish()
    }

}