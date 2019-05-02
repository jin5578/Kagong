package com.tistory.jeongs0222.kagongapplication.ui.written.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemReviewWrittenBinding
import com.tistory.jeongs0222.kagongapplication.model.host.writtenReview.WrittenReviewResult
import com.tistory.jeongs0222.kagongapplication.ui.written.WrittenEventListener


class WrittenReviewAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: WrittenEventListener
): ListAdapter<WrittenReviewResult, WrittenReviewAdapter.ViewHolder>(WReviewDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewWrittenBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemReviewWrittenBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: WrittenEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(r: WrittenReviewResult) {
            binding.reviewItem = r

            binding.lifecycleOwner = lifecycleOwner

            binding.eventListener = eventListener
            binding.executePendingBindings()
        }
    }

    object WReviewDiff: DiffUtil.ItemCallback<WrittenReviewResult>() {
        override fun areItemsTheSame(
            oldItem: WrittenReviewResult,
            newItem: WrittenReviewResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WrittenReviewResult,
            newItem: WrittenReviewResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}