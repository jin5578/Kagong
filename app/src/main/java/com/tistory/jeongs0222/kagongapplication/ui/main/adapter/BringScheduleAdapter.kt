package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemMyScheduleBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResult
import com.tistory.jeongs0222.kagongapplication.ui.main.MainEventListener


class BringScheduleAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<BringScheduleResult, BringScheduleAdapter.ViewModel>(
    MyScheduleList
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val binding = ItemMyScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewModel(
            binding,
            lifecycleOwner,
            eventListener
        )
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.setIsRecyclable(true)

        val item = getItem(position)

        holder.bind(item)
    }


    class ViewModel(
        private val binding: ItemMyScheduleBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(i: BringScheduleResult) {
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