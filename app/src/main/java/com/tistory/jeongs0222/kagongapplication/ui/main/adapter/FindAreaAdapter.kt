package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemFindAreaBinding
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResult
import com.tistory.jeongs0222.kagongapplication.ui.main.MainEventListener


class FindAreaAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<FindAreaResult, FindAreaAdapter.ViewHolder>(
    AreaDiff
) {

    private val TAG = "FindAreaAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFindAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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
        private val binding: ItemFindAreaBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(a: FindAreaResult) {
            binding.areaItem = a

            binding.eventListener = eventListener
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object AreaDiff: DiffUtil.ItemCallback<FindAreaResult>() {
        override fun areItemsTheSame(
            oldItem: FindAreaResult,
            newItem: FindAreaResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FindAreaResult,
            newItem: FindAreaResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}