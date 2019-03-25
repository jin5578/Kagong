package com.tistory.jeongs0222.kagongapplication.ui.notice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemNoticeBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResult
import com.tistory.jeongs0222.kagongapplication.ui.notice.NoticeEventListener


class NoticeListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: NoticeEventListener
): ListAdapter<BringNoticeResult, NoticeListAdapter.ViewHolder>(NoticeDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemNoticeBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: NoticeEventListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(n: BringNoticeResult) {
            binding.noticeItem = n

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object NoticeDiff : DiffUtil.ItemCallback<BringNoticeResult>() {
        override fun areItemsTheSame(
            oldItem: BringNoticeResult,
            newItem: BringNoticeResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringNoticeResult,
            newItem: BringNoticeResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}