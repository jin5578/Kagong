package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.CalendarListAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleViewModel
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

        calendarListAdapter = CalendarListAdapter(this@AddScheduleActivity, addScheduleViewModel)

        viewDataBinding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)

            adapter = calendarListAdapter
        }

        addScheduleViewModel.bind(
            MessageProviderImpl(this) as MessageProvider,
            IntentProviderImpl(this) as IntentProvider
        )

        addScheduleViewModel.previousClick.observe(this@AddScheduleActivity, Observer {
            finish()
        })

        addScheduleViewModel.selectDayClick.observe(this@AddScheduleActivity, Observer {
            addScheduleViewModel.addSchedule(area)
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
                viewDataBinding.selectedDay.visibility = View.VISIBLE
                viewDataBinding.selectedDay.text = addScheduleViewModel.startDay.value + " ~ " + addScheduleViewModel.endDay.value!!.substring(5) + " 추가하기"
            } else {
                viewDataBinding.selectedDay.visibility = View.GONE
            }
        })

        viewDataBinding.aViewModel = addScheduleViewModel
        viewDataBinding.setLifecycleOwner(this@AddScheduleActivity)
    }

}
