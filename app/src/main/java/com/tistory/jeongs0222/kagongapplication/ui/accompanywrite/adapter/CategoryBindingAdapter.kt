package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.dump.category.CategoryItem


@BindingAdapter("categoryList")
fun categoryList(recyclerView: RecyclerView, list: MutableList<CategoryItem>?) {
    if(list != null) {
        (recyclerView.adapter as CategoryAdapter).apply {
            submitList(list)
        }
    }
}

@BindingAdapter("recyclerVisibility")
fun recyclerVisibility(recyclerView: RecyclerView, sort: Int) {
    when(sort) {
        0 ->
            recyclerView.visibility = View.VISIBLE
        else ->
            recyclerView.visibility = View.GONE
    }
}
