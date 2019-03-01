package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ItemCalendarDayBinding
import com.tistory.jeongs0222.kagongapplication.databinding.ItemCalendarEmptyBinding
import com.tistory.jeongs0222.kagongapplication.databinding.ItemCalendarHeaderBinding
import com.tistory.jeongs0222.kagongapplication.model.dump.calendar.CalendarDayItem
import com.tistory.jeongs0222.kagongapplication.model.dump.calendar.CalendarItem
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleEventListener
import com.tistory.jeongs0222.kagongapplication.utils.DateFormatter
import java.text.SimpleDateFormat
import java.util.*

class CalendarListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddScheduleEventListener
) : ListAdapter<Any, RecyclerView.ViewHolder>(CalendarDiff) {

    private val TAG = "CalendarListAdapter"

    private val HEADER_TYPE = 0
    private val EMPTY_TYPE = 1
    private val DAY_TYPE = 2

    private val dateFormatter = DateFormatter()

    var startPosition = -10
    var endPosition = -10

    var today: String= "empty"

    init {
        today()
    }

    @SuppressLint("SimpleDateFormat")
    private fun today() {
        val dateFormate = SimpleDateFormat("MM.dd")

        val calendar = Calendar.getInstance()

        this.today = dateFormate.format(calendar.time)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return when (item) {
            is Long -> HEADER_TYPE
            is String -> EMPTY_TYPE
            else -> DAY_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> {
                val binding = ItemCalendarHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                val params = binding.root.layoutParams as StaggeredGridLayoutManager.LayoutParams
                params.isFullSpan = true

                binding.root.layoutParams = params

                HeaderViewHolder(lifecycleOwner, binding, dateFormatter)
            }
            EMPTY_TYPE -> {
                val binding = ItemCalendarEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                EmptyViewHolder(lifecycleOwner, binding)
            }
            else -> {
                val binding = ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                DayViewHolder(lifecycleOwner, eventListener, binding, dateFormatter, today)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val viewType = getItemViewType(position)

        if(position == startPosition) {
            holder.itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.day_selected_background)
        }

        if(position == endPosition) {
            holder.itemView.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.day_selected_background)
        }

        if(viewType == HEADER_TYPE) {
            val item = getItem(position)

            if(item is Long) {
                (holder as HeaderViewHolder).bind(item)
            }
        } else if(viewType == EMPTY_TYPE) {
            (holder as EmptyViewHolder).bind()
        } else {
            val item = getItem(position)

            if(item is Calendar) {
                (holder as DayViewHolder).bind(item, position)
            }
        }

    }

    class HeaderViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: ItemCalendarHeaderBinding,
        private val dateFormatter: DateFormatter
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(month: Long) {
            binding.monthItem = CalendarItem(dateFormatter.getDate(month, dateFormatter.CALENDAR_HEADER_FORMAT))

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    class EmptyViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: ItemCalendarEmptyBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    class DayViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddScheduleEventListener,
        private val binding: ItemCalendarDayBinding,
        private val dateFormatter: DateFormatter,
        private val today: String
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Calendar, position: Int) {
            val gregorianCalendar = GregorianCalendar(
                day.get(Calendar.YEAR), day.get(Calendar.MONTH), day.get(
                    Calendar.DAY_OF_MONTH
                ), 0, 0, 0
            )
            val day = dateFormatter.getDate(gregorianCalendar.timeInMillis, dateFormatter.CALENDAR_FORMAT)

            binding.dayItem = CalendarDayItem(dateFormatter.getDate(gregorianCalendar.timeInMillis, dateFormatter.CALENDAR_DAY_FORMAT), position, gregorianCalendar)

            if(today == day) {
                binding.day.apply {
                    setTextColor(ContextCompat.getColor(binding.root.context, R.color.colorRed))
                }
            }

            binding.eventListener = eventListener

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object CalendarDiff: DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem == newItem
        }
    }
}
