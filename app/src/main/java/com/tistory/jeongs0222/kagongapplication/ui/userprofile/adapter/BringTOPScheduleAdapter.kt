package com.tistory.jeongs0222.kagongapplication.ui.userprofile.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.databinding.ItemTopScheduleBinding
import com.tistory.jeongs0222.kagongapplication.model.host.bringTOPSchedule.BringTOPScheduleResult


class BringTOPScheduleAdapter(
    private val activity: Activity,
    private val lifecycleOwner: LifecycleOwner
): ListAdapter<BringTOPScheduleResult, BringTOPScheduleAdapter.ViewHolder>(TOPDiff) {

    private val screenWidth: Int

    init {
        val wm = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BringTOPScheduleAdapter.ViewHolder {
        val binding = ItemTopScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner)
    }


    override fun onBindViewHolder(holder: BringTOPScheduleAdapter.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.bind(getItem(position))


        if (itemCount > 1 && position % 2 == 0) {
            val layoutParams = holder.binding.cardView.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.topMargin = 200

            holder.binding.cardView.layoutParams = layoutParams
        }


        Glide.with(holder.binding.image)
            .asBitmap()
            .load(getItem(position).image)
            .apply(RequestOptions.overrideOf(screenWidth / 2, 600).centerCrop())
            .into(holder.binding.image)
    }

    class ViewHolder(
        val binding: ItemTopScheduleBinding,
        private val lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(t: BringTOPScheduleResult) {
            binding.scheduleItem = t

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object TOPDiff : DiffUtil.ItemCallback<BringTOPScheduleResult>() {
        override fun areItemsTheSame(
            oldItem: BringTOPScheduleResult,
            newItem: BringTOPScheduleResult
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BringTOPScheduleResult,
            newItem: BringTOPScheduleResult
        ): Boolean {
            return oldItem == newItem
        }
    }
}