package com.tistory.jeongs0222.kagongapplication.ui.addlocation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemFindLocationBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.AddLocationEventListener


class FindLocationAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddLocationEventListener
): ListAdapter<BringAreaLocationResult, FindLocationAdapter.ViewHolder>(
    LocationDiff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFindLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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
        private val binding: ItemFindLocationBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddLocationEventListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(l: BringAreaLocationResult) {
            binding.locationItem = l

            binding.eventListener = eventListener
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object LocationDiff: DiffUtil.ItemCallback<BringAreaLocationResult>() {
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