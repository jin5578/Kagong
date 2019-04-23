package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemMainAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResult
import com.tistory.jeongs0222.kagongapplication.ui.main.MainEventListener


class AccompanyAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
): ListAdapter<BringAccompanyResult, AccompanyAdapter.ViewHolder>(AccompanyDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMainAccompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemMainAccompanyBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
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