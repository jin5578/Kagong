package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAreaListBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaList.BringAreaListResult
import com.tistory.jeongs0222.kagongapplication.ui.main.MainEventListener


class BringAreaListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
): ListAdapter<BringAreaListResult, BringAreaListAdapter.ViewHolder>(AreaListDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }


    class ViewHolder(
        private val binding: ItemAreaListBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(al: BringAreaListResult) {
            binding.areaItem = al

            binding.eventListener = eventListener
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object AreaListDiff: DiffUtil.ItemCallback<BringAreaListResult>() {
        override fun areItemsTheSame(
            oldItem: BringAreaListResult,
            newItem: BringAreaListResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringAreaListResult,
            newItem: BringAreaListResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}