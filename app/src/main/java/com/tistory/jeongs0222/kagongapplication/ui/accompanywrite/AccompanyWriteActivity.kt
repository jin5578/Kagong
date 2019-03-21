package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAccompanyWriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccompanyWriteActivity: BaseActivity<ActivityAccompanyWriteBinding>() {

    override val layoutResourceId: Int = R.layout.activity_accompany_write

    private val accompanyWriteViewModel by viewModel<AccompanyWriteViewModel>()

    private lateinit var area: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        accompanyWriteViewModel.bind(area)

        accompanyWriteViewModel.bringNickname()

        viewDataBinding.area.text = "#$area"

        accompanyWriteViewModel.previousClick.observe(this@AccompanyWriteActivity, Observer {
            finish()
        })

        accompanyWriteViewModel.userNickname.observe(this@AccompanyWriteActivity, Observer {
            viewDataBinding.nickname.text = it
        })

        viewDataBinding.awViewModel = accompanyWriteViewModel
        viewDataBinding.lifecycleOwner = this@AccompanyWriteActivity
    }

    override fun onBackPressed() {
        finish()
    }
}