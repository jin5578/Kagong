package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.CalendarListAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddScheduleActivity : BaseActivity<ActivityAddScheduleBinding>(){

    override val layoutResourceId: Int = R.layout.activity_add_schedule

    private val addScheduleViewModel by viewModel<AddScheduleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addScheduleViewModel.calendarList.observe(this@AddScheduleActivity, Observer {
            if(it.size != 0) {
                viewDataBinding.recyclerView.apply {
                    Log.e("123", "123")
                    layoutManager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)

                    adapter = CalendarListAdapter(this@AddScheduleActivity, addScheduleViewModel, it)
                }
                //이 부분에 RecyclerView Apply를 해준다.
            }
        })

        viewDataBinding.aViewModel = addScheduleViewModel
        viewDataBinding.setLifecycleOwner(this@AddScheduleActivity)
    }

}
