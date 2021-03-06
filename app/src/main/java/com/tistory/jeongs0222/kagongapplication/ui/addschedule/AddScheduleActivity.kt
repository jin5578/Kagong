package com.tistory.jeongs0222.kagongapplication.ui.addschedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.addschedule.adapter.CalendarListAdapter
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddScheduleActivity : BaseActivity<ActivityAddScheduleBinding>() {

    private val TAG = "AddScheduleActivity"

    override val layoutResourceId: Int = R.layout.activity_add_schedule

    private val addScheduleViewModel by viewModel<AddScheduleViewModel>()

    private lateinit var calendarChangeProvider: CalendarChangeProvider

    private lateinit var calendarListAdapter: CalendarListAdapter

    private lateinit var area: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        calendarChangeProvider = CalendarChangeProviderImpl(viewDataBinding, this@AddScheduleActivity)

        calendarListAdapter = CalendarListAdapter(
            this@AddScheduleActivity,
            addScheduleViewModel
        )

        viewDataBinding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)

            adapter = calendarListAdapter
        }

        addScheduleViewModel.bind(
            MessageProviderImpl(this@AddScheduleActivity) as MessageProvider,
            IntentProviderImpl(this@AddScheduleActivity) as IntentProvider,
            DBHelperProviderImpl(this@AddScheduleActivity) as DBHelperProvider,
            area
        )

        addScheduleViewModel.previousClick.observe(this@AddScheduleActivity, Observer {
            finish()
        })

        addScheduleViewModel.startPosition.observe(this@AddScheduleActivity, Observer {
            if (it != 0) {
                val startPosition = addScheduleViewModel.startPosition.value!!

                calendarChangeProvider.calendarSelected(startPosition)

                calendarListAdapter.startPosition = startPosition
            }
        })

        addScheduleViewModel.endPosition.observe(this@AddScheduleActivity, Observer {
            if (it != 0) {
                val endPosition = addScheduleViewModel.endPosition.value!!

                calendarChangeProvider.calendarSelected(endPosition)

                calendarListAdapter.endPosition = endPosition
            }
        })

        addScheduleViewModel.positionChange.observe(this@AddScheduleActivity, Observer {
            if (it == 0) {
                calendarChangeProvider.calendarDeselected(addScheduleViewModel.lastStartPosition)
            } else if (it == 1) {
                calendarChangeProvider.calendarDeselected(addScheduleViewModel.lastEndPosition)

                calendarListAdapter.endPosition = -10
            }
        })

        addScheduleViewModel.bothSelected.observe(this@AddScheduleActivity, Observer {
            if(it) {
                viewDataBinding.selectedDay.text = addScheduleViewModel.tempStartDay.value!!.replace("-", ".") + " ~ " + addScheduleViewModel.tempEndDay.value!!.substring(5).replace("-", ".") + " 추가하기"
            }
        })

        viewDataBinding.aViewModel = addScheduleViewModel
        viewDataBinding.lifecycleOwner = this@AddScheduleActivity
    }

    override fun onBackPressed() {
        finish()
    }

}
