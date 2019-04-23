package com.tistory.jeongs0222.kagongapplication.ui.written

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityWrittenBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.written.adapter.WrittenAccompanyAdapter
import com.tistory.jeongs0222.kagongapplication.utils.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WrittenActivity: BaseActivity<ActivityWrittenBinding>() {

    override val layoutResourceId: Int = R.layout.activity_written

    private val writtenViewModel by viewModel<WrittenViewModel>()

    private val constraintSetProvider = ConstraintSetProviderImpl(this@WrittenActivity) as ConstraintSetProvider
    private val messageProvider = MessageProviderImpl(this@WrittenActivity) as MessageProvider

    private lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        writtenViewModel.bind(DBHelperProviderImpl(this@WrittenActivity), messageProvider)

        viewDataBinding.recycler.apply {
            layoutManager = LinearLayoutManager(this@WrittenActivity)
            adapter = WrittenAccompanyAdapter(this@WrittenActivity, writtenViewModel)
        }

        writtenViewModel.previousClick.observe(this@WrittenActivity, Observer {
            finish()
        })

        writtenViewModel.finishRequest.observe(this@WrittenActivity, Observer {
            finish()
        })

        writtenViewModel.moreVisibility.observe(this@WrittenActivity, Observer {
            if(it)
                constraintSetProvider.moreExpandAnimation(R.layout.layout_written_more_expand, viewDataBinding.includeWrittenMore.moreLayout)
            else
                constraintSetProvider.moreContractAnimation(R.layout.layout_written_more_contract, viewDataBinding.includeWrittenMore.moreLayout)
        })

        writtenViewModel.moreDeleteClick.observe(this@WrittenActivity, Observer {
            val builder = AlertDialog.Builder(this@WrittenActivity)

            val inflater = layoutInflater.inflate(R.layout.custom_alertdialog_layout, null)
            inflater.findViewById<TextView>(R.id.content).text = "삭제된 글은 복구할 수 없습니다."

            builder.setView(inflater)

            inflater.findViewById<TextView>(R.id.cancel).setOnClickListener {
                alertDialog.dismiss()
            }

            inflater.findViewById<TextView>(R.id.check).setOnClickListener {
                writtenViewModel.deleteAccompany()
            }

            alertDialog = builder.create()

            alertDialog.show()

        })

        viewDataBinding.wViewModel = writtenViewModel
        viewDataBinding.lifecycleOwner = this@WrittenActivity
    }

    override fun onBackPressed() {
        finish()
    }

}