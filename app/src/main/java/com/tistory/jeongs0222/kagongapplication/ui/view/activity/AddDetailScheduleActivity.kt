package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.BringDetailScheduleAdapter
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
    private val dynamicProvider = DynamicProviderImpl(this@AddDetailScheduleActivity) as DynamicProvider

    private lateinit var area: String


    @SuppressLint("InflateParams", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AddDetailScheduleActivity)
            adapter = BringDetailScheduleAdapter(this@AddDetailScheduleActivity, addDetailScheduleViewModel, dynamicProvider)
        }

        addDetailScheduleViewModel.bind(intentProvider, messageProvider)

        //addDetailScheduleViewModel.bringDetailSchedule(area)

        addDetailScheduleViewModel.detailSchedule.observe(this@AddDetailScheduleActivity, Observer {
            if(it.size == 0) {
                viewDataBinding.itemNull.visibility = View.VISIBLE
            } else {
                viewDataBinding.itemNull.visibility = View.GONE
            }
        })

        addDetailScheduleViewModel.moreVisibility.observe(this@AddDetailScheduleActivity, Observer {
            if (it) {
                constraintSetProvider.moreExpandAnimation(R.layout.layout_more_expand, viewDataBinding.includeMore.moreLayout)
            } else {
                constraintSetProvider.moreContractAnimation(R.layout.layout_more_contract, viewDataBinding.includeMore.moreLayout)
            }
        })

        addDetailScheduleViewModel.moreLocationVisibility.observe(this@AddDetailScheduleActivity, Observer {
            if(it) {
                constraintSetProvider.moreExpandAnimation(R.layout.layout_location_more_expand, viewDataBinding.includeLocationMore.moreLayout)
            } else {
                constraintSetProvider.moreContractAnimation(R.layout.layout_location_more_contract, viewDataBinding.includeLocationMore.moreLayout)
            }
        })

        addDetailScheduleViewModel.previousClick.observe(this@AddDetailScheduleActivity, Observer {
            finish()
        })

        addDetailScheduleViewModel.editScheduleClick.observe(this@AddDetailScheduleActivity, Observer {
            intentProvider.intentPutExtra(AddScheduleActivity::class.java, area)
        })

        addDetailScheduleViewModel.deleteScheduleClick.observe(this@AddDetailScheduleActivity, Observer {
            messageProvider.addDetailScheduleAlertDialog(addDetailScheduleViewModel, area)
        })

        addDetailScheduleViewModel.editLocationClick.observe(this@AddDetailScheduleActivity, Observer {
            intentProvider.intentPutTwoExtra(AddLocationActivity::class.java, area, addDetailScheduleViewModel.selectedOrder.value!!)
        })

        viewDataBinding.adViewModel = addDetailScheduleViewModel
        viewDataBinding.lifecycleOwner = this@AddDetailScheduleActivity
    }

    override fun onResume() {
        super.onResume()
        addDetailScheduleViewModel.bringDetailSchedule(area)
    }

    override fun onBackPressed() {
        finish()
    }
}
