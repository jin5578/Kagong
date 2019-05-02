package com.tistory.jeongs0222.kagongapplication.ui.written.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.writtenReview.WrittenReviewResult


@BindingAdapter("writtenReviewList")
fun writtenReviewList(recyclerView: RecyclerView, list: MutableList<WrittenReviewResult>?) {
    if(list != null) {
        (recyclerView.adapter as WrittenReviewAdapter).apply {
            submitList(list)
        }
    }
}