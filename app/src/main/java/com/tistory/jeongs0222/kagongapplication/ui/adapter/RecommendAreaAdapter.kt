package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemRecommendedAreaBinding
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainEventListener


class RecommendAreaAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener
) : ListAdapter<RecommendAreaResult, RecommendAreaAdapter.ViewHolder>(RecommendAreaDiff) {

    private val TAG = "RecommendAreaAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendedAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemRecommendedAreaBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(r: RecommendAreaResult) {
            binding.recommendItem = r

            binding.eventListener = eventListener
            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object RecommendAreaDiff: DiffUtil.ItemCallback<RecommendAreaResult>() {
        override fun areItemsTheSame(
            oldItem: RecommendAreaResult,
            newItem: RecommendAreaResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecommendAreaResult,
            newItem: RecommendAreaResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}