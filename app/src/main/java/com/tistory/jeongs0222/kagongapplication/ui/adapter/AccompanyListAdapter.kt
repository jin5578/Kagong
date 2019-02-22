package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.accompanylist.AccompanyListResult
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAccompanyListBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailEventListener


class AccompanyListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AreaDetailEventListener
) : ListAdapter<AccompanyListResult, AccompanyListAdapter.ViewHolder>(listDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccompanyListAdapter.ViewHolder {
        val binding = ItemAccompanyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: AccompanyListAdapter.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemAccompanyListBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AreaDetailEventListener
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(a: AccompanyListResult) {
            binding.accompanyListItem = a

            binding.eventListener = eventListener
            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }

    }

    object listDiff : DiffUtil.ItemCallback<AccompanyListResult>() {

        override fun areItemsTheSame(
            oldItem: AccompanyListResult,
            newItem: AccompanyListResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AccompanyListResult,
            newItem: AccompanyListResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}