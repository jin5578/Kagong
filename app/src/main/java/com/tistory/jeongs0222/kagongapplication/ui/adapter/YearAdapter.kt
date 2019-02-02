package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.YearItemBinding
import com.tistory.jeongs0222.kagongapplication.model.year.YearItem
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterEventListener


class YearAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: RegisterEventListener,
    private val items: MutableList<YearItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "YearAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = YearItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        if (items.isNotEmpty()) {
            (holder as ViewHolder).bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: YearItemBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: RegisterEventListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(y: YearItem) {
            binding.yearItem = y

            binding.eventListener = eventListener
            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }
    }
}