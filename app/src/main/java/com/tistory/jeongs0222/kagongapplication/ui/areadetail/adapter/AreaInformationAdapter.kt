package com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAreaInformationBinding


class AreaInformationAdapter(
    private val lifecycleOwner: LifecycleOwner
): ListAdapter<AreaInformationResult, AreaInformationAdapter.ViewHolder>(
    InformationDiff
) {

    private val TAG = "AreaInformationAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(
            binding,
            lifecycleOwner
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        if(position != 6) {
            holder.bind(getItem(position))
        }
    }

    class ViewHolder(
        private val binding: ItemAreaInformationBinding,
        private val lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(i: AreaInformationResult) {
            binding.informationItem = i

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object InformationDiff: DiffUtil.ItemCallback<AreaInformationResult>() {
        override fun areItemsTheSame(
            oldItem: AreaInformationResult,
            newItem: AreaInformationResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AreaInformationResult,
            newItem: AreaInformationResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}