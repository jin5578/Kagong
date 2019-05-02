package com.tistory.jeongs0222.kagongapplication.ui.adddetailschedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.adddetailschedule.adapter.BringDetailScheduleAdapter
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.AddLocationActivity
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.addschedule.AddScheduleActivity
import com.tistory.jeongs0222.kagongapplication.ui.howToUse.HowToUseActivity
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
    private val dbHelperProvider = DBHelperProviderImpl(this@AddDetailScheduleActivity) as DBHelperProvider

    private lateinit var bringDetailScheduleAdapter: BringDetailScheduleAdapter

    private lateinit var area: String


    @SuppressLint("InflateParams", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        bringDetailScheduleAdapter =
                BringDetailScheduleAdapter(
                    this@AddDetailScheduleActivity,
                    addDetailScheduleViewModel,
                    dynamicProvider
                )

        addDetailScheduleViewModel.bind(intentProvider, messageProvider, dbHelperProvider)

        viewDataBinding.recyclerView.layoutManager = LinearLayoutManager(this@AddDetailScheduleActivity)

        addDetailScheduleViewModel.howToUseClick.observe(this@AddDetailScheduleActivity, Observer {
            intentProvider.iIntent(HowToUseActivity::class.java, 1)
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

            constraintSetProvider.moreContractAnimation(R.layout.layout_more_contract, viewDataBinding.includeMore.moreLayout)
        })

        addDetailScheduleViewModel.deleteScheduleClick.observe(this@AddDetailScheduleActivity, Observer {
            messageProvider.addDetailScheduleAlertDialog(addDetailScheduleViewModel, area, "0", 0)
        })

        addDetailScheduleViewModel.editLocationClick.observe(this@AddDetailScheduleActivity, Observer {
            intentProvider.intentPutTwoExtra(AddLocationActivity::class.java, area, addDetailScheduleViewModel.selectedOrder.value!!)

            constraintSetProvider.moreContractAnimation(R.layout.layout_location_more_contract, viewDataBinding.includeLocationMore.moreLayout)
        })

        addDetailScheduleViewModel.deleteLocationClick.observe(this@AddDetailScheduleActivity, Observer {
            messageProvider.addDetailScheduleAlertDialog(addDetailScheduleViewModel, area, addDetailScheduleViewModel.selectedOrder.value!!, 1)
        })

        viewDataBinding.adViewModel = addDetailScheduleViewModel
        viewDataBinding.lifecycleOwner = this@AddDetailScheduleActivity
    }

    override fun onResume() {
        super.onResume()
        addDetailScheduleViewModel.bringDetailSchedule(area)

        viewDataBinding.recyclerView.adapter =
                BringDetailScheduleAdapter(
                    this@AddDetailScheduleActivity,
                    addDetailScheduleViewModel,
                    dynamicProvider
                )
    }

    override fun onBackPressed() {
        finish()
    }
}
