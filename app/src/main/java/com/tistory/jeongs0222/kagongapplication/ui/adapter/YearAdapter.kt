package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemYearBinding
import com.tistory.jeongs0222.kagongapplication.model.dump.year.YearItem
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterEventListener


class YearAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: RegisterEventListener
) : ListAdapter<YearItem, YearAdapter.ViewHolder>(yearDiff) {

    private val TAG = "YearAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemYearBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemYearBinding,
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

    object yearDiff: DiffUtil.ItemCallback<YearItem>() {
        override fun areItemsTheSame(
            oldItem: YearItem,
            newItem: YearItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: YearItem,
            newItem: YearItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}
