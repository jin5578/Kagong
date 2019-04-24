package com.tistory.jeongs0222.kagongapplication.ui.written.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAccompanyWrittenBinding
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResult
import com.tistory.jeongs0222.kagongapplication.ui.written.WrittenEventListener


class WrittenAccompanyAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: WrittenEventListener
): ListAdapter<WrittenAccompanyResult, WrittenAccompanyAdapter.ViewHolder>(WAccompanyDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccompanyWrittenBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemAccompanyWrittenBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: WrittenEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(wA: WrittenAccompanyResult) {
            binding.accompanyItem = wA

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object WAccompanyDiff: DiffUtil.ItemCallback<WrittenAccompanyResult>() {
        override fun areItemsTheSame(
            oldItem: WrittenAccompanyResult,
            newItem: WrittenAccompanyResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WrittenAccompanyResult,
            newItem: WrittenAccompanyResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}