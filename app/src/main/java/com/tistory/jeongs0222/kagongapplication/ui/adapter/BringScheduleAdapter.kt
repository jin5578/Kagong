package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemMyScheduleBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainEventListener


class BringScheduleAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<BringScheduleResult, BringScheduleAdapter.ViewModel>(MyScheduleList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val binding = ItemMyScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewModel(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.setIsRecyclable(false)

        val item = getItem(position)

        holder.bind(item)
    }


    class ViewModel(
        private val binding: ItemMyScheduleBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(i: BringScheduleResult) {
            when(i.type) {
                "past" -> binding.title.text = "지난 여행"
                "future" -> binding.title.text = "다가오는 여행"
                "present" -> binding.title.text = "여행중"
            }

            binding.scheduleItem = i
            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object MyScheduleList : DiffUtil.ItemCallback<BringScheduleResult>() {
        override fun areItemsTheSame(
            oldItem: BringScheduleResult,
            newItem: BringScheduleResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringScheduleResult,
            newItem: BringScheduleResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}