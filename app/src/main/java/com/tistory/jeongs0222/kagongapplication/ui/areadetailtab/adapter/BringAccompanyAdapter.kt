package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResult
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabEventListener


class BringAccompanyAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AreaDetailTabEventListener
): ListAdapter<BringAccompanyResult, BringAccompanyAdapter.ViewHolder>(AccompanyDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemAccompanyBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AreaDetailTabEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(a: BringAccompanyResult) {
            binding.accompanyItem = a

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object AccompanyDiff: DiffUtil.ItemCallback<BringAccompanyResult>() {
        override fun areItemsTheSame(
            oldItem: BringAccompanyResult,
            newItem: BringAccompanyResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringAccompanyResult,
            newItem: BringAccompanyResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}