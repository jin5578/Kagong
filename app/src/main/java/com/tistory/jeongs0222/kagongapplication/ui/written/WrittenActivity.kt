package com.tistory.jeongs0222.kagongapplication.ui.written

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityWrittenBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.written.adapter.WrittenAccompanyAdapter
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class WrittenActivity: BaseActivity<ActivityWrittenBinding>() {

    override val layoutResourceId: Int = R.layout.activity_written

    private val writtenViewModel by viewModel<WrittenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        writtenViewModel.bind(DBHelperProviderImpl(this@WrittenActivity))

        viewDataBinding.recycler.apply {
            layoutManager = LinearLayoutManager(this@WrittenActivity)
            adapter = WrittenAccompanyAdapter(this@WrittenActivity, writtenViewModel)
        }

        writtenViewModel.previousClick.observe(this@WrittenActivity, Observer {
            finish()
        })

        viewDataBinding.wViewModel = writtenViewModel
        viewDataBinding.lifecycleOwner = this@WrittenActivity
    }

    override fun onBackPressed() {
        finish()
    }

}