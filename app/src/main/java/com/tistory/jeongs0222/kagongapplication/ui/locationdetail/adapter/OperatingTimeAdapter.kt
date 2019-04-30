package com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemOperatingTimeBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringOperatingTime.BringOperatingTimeResult


class OperatingTimeAdapter(
    private val lifecycleOwner: LifecycleOwner
): ListAdapter<BringOperatingTimeResult, OperatingTimeAdapter.ViewHolder>(TimeDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperatingTimeAdapter.ViewHolder {
        val binding = ItemOperatingTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: OperatingTimeAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemOperatingTimeBinding,
        private val lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(t: BringOperatingTimeResult) {
            binding.timeItem = t

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object TimeDiff: DiffUtil.ItemCallback<BringOperatingTimeResult>() {
        override fun areItemsTheSame(
            oldItem: BringOperatingTimeResult,
            newItem: BringOperatingTimeResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringOperatingTimeResult,
            newItem: BringOperatingTimeResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}