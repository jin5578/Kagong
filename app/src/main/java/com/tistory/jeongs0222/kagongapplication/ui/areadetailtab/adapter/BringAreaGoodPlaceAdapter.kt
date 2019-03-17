package com.tistory.jeongs0222.kagongapplication.ui.view.areadetailtab.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAreaGoodplaceBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResult
import com.tistory.jeongs0222.kagongapplication.ui.view.areadetailtab.AreaDetailTabEventListener


class BringAreaGoodPlaceAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AreaDetailTabEventListener
): ListAdapter<BringAreaGoodPlaceResult, BringAreaGoodPlaceAdapter.ViewHolder>(
    AreaGoodPlaceDiff
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaGoodplaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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
        private val binding: ItemAreaGoodplaceBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AreaDetailTabEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(g: BringAreaGoodPlaceResult) {
            binding.areaGoodPlaceItem = g

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object AreaGoodPlaceDiff: DiffUtil.ItemCallback<BringAreaGoodPlaceResult>() {
        override fun areItemsTheSame(
            oldItem: BringAreaGoodPlaceResult,
            newItem: BringAreaGoodPlaceResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringAreaGoodPlaceResult,
            newItem: BringAreaGoodPlaceResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}