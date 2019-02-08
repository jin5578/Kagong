package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.AreaSearchHistoryItemBinding
import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainEventListener


class AreaSearchHistoryAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener) :
    ListAdapter<AreaSearchResult, AreaSearchHistoryAdapter.ViewHolder>(areaDiff) {

    private val TAG = "SearchHistoryAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AreaSearchHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        if(holder.layoutPosition == 0) {
            holder.bind(AreaSearchResult("도시 검색"))
        } else {
            holder.bind(getItem(position - 1))
        }
    }

    class ViewHolder(
        private val binding: AreaSearchHistoryItemBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: MainEventListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(a: AreaSearchResult) {
            binding.areaItem = a

            binding.eventListener = eventListener
            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }
    }

    object areaDiff : DiffUtil.ItemCallback<AreaSearchResult>() {
        override fun areItemsTheSame(
            oldItem: AreaSearchResult,
            newItem: AreaSearchResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AreaSearchResult,
            newItem: AreaSearchResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}
