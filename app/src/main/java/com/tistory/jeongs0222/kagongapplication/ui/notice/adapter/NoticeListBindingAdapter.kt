package com.tistory.jeongs0222.kagongapplication.ui.notice.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResult


@BindingAdapter("noticeList")
fun noticeList(recyclerView: RecyclerView, list: MutableList<BringNoticeResult>?) {
    if (list != null) {
        (recyclerView.adapter as NoticeListAdapter).apply {
            submitList(list)
        }
    }
}