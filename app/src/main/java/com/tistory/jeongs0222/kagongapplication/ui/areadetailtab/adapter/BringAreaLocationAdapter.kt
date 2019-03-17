package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAreaLocationBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabEventListener


class BringAreaLocationAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AreaDetailTabEventListener
): ListAdapter<BringAreaLocationResult, BringAreaLocationAdapter.ViewHolder>(
    AreaLocationDiff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(
            binding,
            lifecycleOwner,
            eventListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemAreaLocationBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AreaDetailTabEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(b: BringAreaLocationResult) {
            binding.areaLocationItem = b

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object AreaLocationDiff: DiffUtil.ItemCallback<BringAreaLocationResult>() {
        override fun areItemsTheSame(
            oldItem: BringAreaLocationResult,
            newItem: BringAreaLocationResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringAreaLocationResult,
            newItem: BringAreaLocationResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}