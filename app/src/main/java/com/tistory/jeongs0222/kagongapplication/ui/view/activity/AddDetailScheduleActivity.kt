package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddDetailScheduleViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDetailScheduleActivity : BaseActivity<ActivityAddDetailScheduleBinding>() {

    private val TAG = "AddDetailScheduleActivity"

    override val layoutResourceId: Int = R.layout.activity_add_detail_schedule

    private val addDetailScheduleViewModel by viewModel<AddDetailScheduleViewModel>()

    private val intentProvider = IntentProviderImpl(this@AddDetailScheduleActivity) as IntentProvider
    private val messageProvider = MessageProviderImpl(this@AddDetailScheduleActivity) as MessageProvider
    private val constraintSetProvider = ConstraintSetProviderImpl(this@AddDetailScheduleActivity) as ConstraintSetProvider

    private lateinit var area: String

    private lateinit var alertDialog: AlertDialog


    @SuppressLint("InflateParams", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        addDetailScheduleViewModel.bind(intentProvider, messageProvider)


        addDetailScheduleViewModel.moreVisibility.observe(this@AddDetailScheduleActivity, Observer {
            if (it) {
                constraintSetProvider.moreExpandAnimation(R.layout.layout_floating_invisible, viewDataBinding.includeFloating.moreFloating)
                constraintSetProvider.moreExpandAnimation(R.layout.layout_more_expand, viewDataBinding.includeMore.moreLayout)

                //viewDataBinding.floating.visibility = View.GONE
            } else {
                constraintSetProvider.moreContractAnimation(R.layout.layout_more_contract, viewDataBinding.includeMore.moreLayout)
                constraintSetProvider.moreContractAnimation(R.layout.layout_floating_visible, viewDataBinding.includeFloating.moreFloating)

                //viewDataBinding.floating.visibility = View.VISIBLE
            }
        })

        addDetailScheduleViewModel.previousClick.observe(this@AddDetailScheduleActivity, Observer {
            finish()
        })

        addDetailScheduleViewModel.floatingClick.observe(this@AddDetailScheduleActivity, Observer {
            intentProvider.intentPutExtra(AddLocationActivity::class.java, area)
        })

        addDetailScheduleViewModel.editScheduleClick.observe(this@AddDetailScheduleActivity, Observer {
            intentProvider.intentPutExtra(AddScheduleActivity::class.java, area)
        })

        addDetailScheduleViewModel.deleteScheduleClick.observe(this@AddDetailScheduleActivity, Observer {
            val builder = AlertDialog.Builder(this@AddDetailScheduleActivity)
            val inflater = layoutInflater.inflate(R.layout.custom_alertdialog_layout, null)

            builder.setView(inflater)

            inflater.findViewById<TextView>(R.id.cancel).setOnClickListener {
                alertDialog.dismiss()
            }

            inflater.findViewById<TextView>(R.id.check).setOnClickListener {
                addDetailScheduleViewModel.deleteSchedule(area)
            }

            alertDialog = builder.create()

            alertDialog.show()
        })

        viewDataBinding.adViewModel = addDetailScheduleViewModel
        viewDataBinding.lifecycleOwner = this@AddDetailScheduleActivity
    }

    override fun onBackPressed() {
        finish()
    }
}
