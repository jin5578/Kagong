package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddDetailScheduleEventListener


class BringDetailScheduleAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddDetailScheduleEventListener
): ListAdapter<BringDetailScheduleResult, BringDetailScheduleAdapter.ViewHolder>(DetailScheduleDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemDetailScheduleBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddDetailScheduleEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(d: BringDetailScheduleResult) {
            binding.detailScheduleItem = d

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object DetailScheduleDiff: DiffUtil.ItemCallback<BringDetailScheduleResult>() {
        override fun areItemsTheSame(
            oldItem: BringDetailScheduleResult,
            newItem: BringDetailScheduleResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringDetailScheduleResult,
            newItem: BringDetailScheduleResult
        ): Boolean {
            return oldItem == newItem
        }

    }
}