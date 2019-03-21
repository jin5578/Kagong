package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemCategoryBinding
import com.tistory.jeongs0222.kagongapplication.model.dump.category.CategoryItem
import com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.AccompanyWriteEventListener


class CategoryAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AccompanyWriteEventListener
): ListAdapter<CategoryItem, CategoryAdapter.ViewHolder>(CategoryDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner, eventListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemCategoryBinding,
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AccompanyWriteEventListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(c: CategoryItem) {

            binding.categoryItem = c

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object CategoryDiff: DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CategoryItem,
            newItem: CategoryItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}