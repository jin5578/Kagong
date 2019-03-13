package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResult
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddDetailScheduleEventListener
import com.tistory.jeongs0222.kagongapplication.utils.DynamicProvider


class BringDetailScheduleAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddDetailScheduleEventListener,
    private val dynamicProvider: DynamicProvider
): ListAdapter<BringDetailScheduleResult, BringDetailScheduleAdapter.ViewHolder>(DetailScheduleDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener, dynamicProvider)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))

        Log.e("position", position.toString())
    }

    class ViewHolder(
        val binding: ItemDetailScheduleBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddDetailScheduleEventListener,
        private val dynamicProvider: DynamicProvider
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(d: BringDetailScheduleResult) {
            if(d.location != "") {
                dynamicProvider.location(binding.entire, stringPreprocessor(d.location))
            }
            binding.detailScheduleItem = d

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }

        private fun stringPreprocessor(location: String): List<String> = location.split(", ")
    }

    object DetailScheduleDiff: DiffUtil.ItemCallback<BringDetailScheduleResult>() {
        override fun areItemsTheSame(
            oldItem: BringDetailScheduleResult,
            newItem: BringDetailScheduleResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringDetailScheduleResult,
            newItem: BringDetailScheduleResult
        ): Boolean {
            return oldItem == newItem
        }

    }
}