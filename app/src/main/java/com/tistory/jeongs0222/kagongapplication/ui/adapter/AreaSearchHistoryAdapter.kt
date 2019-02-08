package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.AreaSearchHistoryItemBinding
import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainEventListener


class AreaSearchHistoryAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: MainEventListener,
    private val items: MutableList<AreaSearchResult>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "SearchHistoryAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = AreaSearchHistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        if(items.isNotEmpty()) {
            if(holder.layoutPosition == 0) {
                (holder as ViewHolder).bind(AreaSearchResult("도시 검색"))
            } else {
                (holder as ViewHolder).bind(items[position-1])
            }
        }
    }

    override fun getItemCount(): Int {
        return if(items.isEmpty()) {
            0
        } else {
            items.size + 1
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
}
