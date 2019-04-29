package com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemLocationReviewBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResult


class LocationReviewAdapter(
    private val lifecycleOwner: LifecycleOwner
): ListAdapter<BringLocationReviewResult, LocationReviewAdapter.ViewHolder>(ReviewDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLocationReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemLocationReviewBinding,
        private val lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(r: BringLocationReviewResult) {
            binding.reviewItem = r

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object ReviewDiff: DiffUtil.ItemCallback<BringLocationReviewResult>() {
        override fun areItemsTheSame(
            oldItem: BringLocationReviewResult,
            newItem: BringLocationReviewResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringLocationReviewResult,
            newItem: BringLocationReviewResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}