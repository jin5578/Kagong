package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.FindAreaItemBinding
import com.tistory.jeongs0222.kagongapplication.model.host.findarea.FindAreaResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainEventListener


class FindAreaAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<FindAreaResult, FindAreaAdapter.ViewHolder>(areaDiff) {

    private val TAG = "FindAreaAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FindAreaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: FindAreaItemBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(a: FindAreaResult) {
            binding.areaItem = a

            binding.eventListener = eventListener
            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }
    }

    object areaDiff: DiffUtil.ItemCallback<FindAreaResult>() {
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