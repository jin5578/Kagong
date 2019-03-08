package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemFindLocationBinding
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationEventListener


class FindLocationAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddLocationEventListener
): ListAdapter<FindLocationResult, FindLocationAdapter.ViewHolder>(LocationDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFindLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
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
        fun bind(l: FindLocationResult) {
            binding.locationItem = l

            binding.eventListener = eventListener
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object LocationDiff: DiffUtil.ItemCallback<FindLocationResult>() {
        override fun areItemsTheSame(
            oldItem: FindLocationResult,
            newItem: FindLocationResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FindLocationResult,
            newItem: FindLocationResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}