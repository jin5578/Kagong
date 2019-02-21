package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.dump.calendar.CalendarList
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleEventListener


/*
class AddScheduleAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddScheduleEventListener
): ListAdapter<CalendarList, RecyclerView.ViewModel>(sDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewModel {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewModel, position: Int) {

    }

    class DayViewModel(
        private val binding:
    )


    object sDiff: DiffUtil.ItemCallback<AreaInformationResult>() {
        override fun areItemsTheSame(
            oldItem: AreaInformationResult,
            newItem: AreaInformationResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AreaInformationResult,
            newItem: AreaInformationResult
        ): Boolean {
            return oldItem == newItem
        }

    }
}*/
