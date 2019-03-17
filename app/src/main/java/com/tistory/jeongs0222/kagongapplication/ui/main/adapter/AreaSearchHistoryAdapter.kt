package com.tistory.jeongs0222.kagongapplication.ui.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemSearchHistoryAreaBinding
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResult
import com.tistory.jeongs0222.kagongapplication.ui.view.main.MainEventListener


class AreaSearchHistoryAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<FindAreaHistoryResult, AreaSearchHistoryAdapter.ViewHolder>(
    AreaDiff
) {

    private val TAG = "AreaSearchAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchHistoryAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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
        private val binding: ItemSearchHistoryAreaBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(a: FindAreaHistoryResult) {
            binding.areaItem = a

            binding.eventListener = eventListener
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object AreaDiff : DiffUtil.ItemCallback<FindAreaHistoryResult>() {
        override fun areItemsTheSame(
            oldItem: FindAreaHistoryResult,
            newItem: FindAreaHistoryResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FindAreaHistoryResult,
            newItem: FindAreaHistoryResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}
