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
import org.koin.androidx.viewmodel.ext.android.viewModel


class WrittenActivity: BaseActivity<ActivityWrittenBinding>() {

    override val layoutResourceId: Int = R.layout.activity_written

    private val wViewModel by viewModel<WrittenViewModel>()

    private val constraintSetProvider = ConstraintSetProviderImpl(this@WrittenActivity) as ConstraintSetProvider
    private val messageProvider = MessageProviderImpl(this@WrittenActivity) as MessageProvider

    private lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wViewModel.wBind(DBHelperProviderImpl(this@WrittenActivity), messageProvider)

        viewDataBinding.recycler.apply {
            layoutManager = LinearLayoutManager(this@WrittenActivity)
            adapter = WrittenAccompanyAdapter(this@WrittenActivity, wViewModel)
        }

        wViewModel.wPreviousClick.observe(this@WrittenActivity, Observer {
            finish()
        })

        wViewModel.wFinishRequest.observe(this@WrittenActivity, Observer {
            finish()
        })

        wViewModel.wMoreVisibility.observe(this@WrittenActivity, Observer {
            if(it)
                constraintSetProvider.moreExpandAnimation(R.layout.layout_written_more_expand, viewDataBinding.includeWrittenMore.moreLayout)
            else
                constraintSetProvider.moreContractAnimation(R.layout.layout_written_more_contract, viewDataBinding.includeWrittenMore.moreLayout)
        })

        wViewModel._wMoreDeleteClick.observe(this@WrittenActivity, Observer {
            deleteAlertDialog()
        })

        viewDataBinding.wViewModel = wViewModel
        viewDataBinding.lifecycleOwner = this@WrittenActivity
    }

    private fun deleteAlertDialog() {
        val builder = AlertDialog.Builder(this@WrittenActivity)

        val inflater = layoutInflater.inflate(R.layout.custom_alertdialog_layout, null)
        inflater.findViewById<TextView>(R.id.content).text = getString(R.string.written_delete_content)

        inflater.findViewById<TextView>(R.id.cancel).setOnClickListener {
            alertDialog.dismiss()
        }

        inflater.findViewById<TextView>(R.id.check).setOnClickListener {
            wViewModel.wDeleteAccompany()
        }

        builder.setView(inflater)

        alertDialog = builder.create()

        alertDialog.show()
    }

    override fun onBackPressed() {
        finish()
    }

}