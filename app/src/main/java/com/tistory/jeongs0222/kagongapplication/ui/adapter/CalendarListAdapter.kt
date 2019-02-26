package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.util.Log
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
import com.tistory.jeongs0222.kagongapplication.ui.view.activity.AddScheduleActivity
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleEventListener
import com.tistory.jeongs0222.kagongapplication.utils.DateFormatter
import java.util.*

class CalendarListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddScheduleEventListener
) : ListAdapter<Any, RecyclerView.ViewHolder>(calendarDiff) {

    private val TAG = "CalendarListAdapter"

    private val HEADER_TYPE = 0
    private val EMPTY_TYPE = 1
    private val DAY_TYPE = 2

    private val dateFormatter = DateFormatter()

    var startPosition = -10
    var endPosition = -10


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

                DayViewHolder(lifecycleOwner, eventListener, binding, dateFormatter)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        Log.e("startPosition", startPosition.toString())
        Log.e("endPosition", endPosition.toString())

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

            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }
    }

    class EmptyViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val binding: ItemCalendarEmptyBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }
    }

    class DayViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddScheduleEventListener,
        private val binding: ItemCalendarDayBinding,
        private val dateFormatter: DateFormatter
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Calendar, position: Int) {
            val gregorianCalendar = GregorianCalendar(
                day.get(Calendar.YEAR), day.get(Calendar.MONTH), day.get(
                    Calendar.DAY_OF_MONTH
                ), 0, 0, 0
            )
            binding.dayItem = CalendarDayItem(dateFormatter.getDate(gregorianCalendar.timeInMillis, dateFormatter.CALENDAR_DAY_FORMAT), position, gregorianCalendar)

            binding.eventListener = eventListener

            binding.setLifecycleOwner(lifecycleOwner)
            binding.executePendingBindings()
        }
    }

    object calendarDiff: DiffUtil.ItemCallback<Any>() {
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


/*
class CalendarListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: AddScheduleEventListener,
    private val calendarList: MutableList<Any>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "CalendarListAdapter"

    private val HEADER_TYPE = 0
    private val EMPTY_TYPE = 1
    private val DAY_TYPE = 2

    private val dateFormatter = DateFormatter()


    override fun getItemViewType(position: Int): Int {
        val item = calendarList[position]

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

                HeaderViewHolder(lifecycleOwner, eventListener, binding, dateFormatter)
            }
            EMPTY_TYPE -> {
                val binding = ItemCalendarEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                EmptyViewHolder(lifecycleOwner, eventListener, binding)
            }
            else -> {
                val binding = ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                DayViewHolder(lifecycleOwner, eventListener, binding, dateFormatter)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val viewType = getItemViewType(position)

        Log.e("viewType", viewType.toString())

        if(viewType == HEADER_TYPE) {
            val item = calendarList[position]

            if(item is Long) {
                (holder as HeaderViewHolder).bind(item)
            }
        } else if(viewType == EMPTY_TYPE) {
            (holder as EmptyViewHolder).bind()
        } else {
            val item = calendarList[position]

            if(item is Calendar) {
                Log.e("123", "123")
                (holder as DayViewHolder).bind(item)
            }
        }

    }

    override fun getItemCount(): Int = calendarList.size


    class HeaderViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddScheduleEventListener,
        private val binding: ItemCalendarHeaderBinding,
        private val dateFormatter: DateFormatter
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(month: Long) {

            binding.header.text = dateFormatter.getDate(month, dateFormatter.CALENDAR_HEADER_FORMAT)
        }
    }

    class EmptyViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddScheduleEventListener,
        private val binding: ItemCalendarEmptyBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    class DayViewHolder(
        private val lifecycleOwner: LifecycleOwner,
        private val eventListener: AddScheduleEventListener,
        private val binding: ItemCalendarDayBinding,
        private val dateFormatter: DateFormatter
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(day: Calendar) {
            val gregorianCalendar = GregorianCalendar(
                day.get(Calendar.YEAR), day.get(Calendar.MONTH), day.get(
                    Calendar.DAY_OF_MONTH
                ), 0, 0, 0
            )

            binding.day.text = dateFormatter.getDate(gregorianCalendar.timeInMillis, dateFormatter.CALENDAR_DAY_FORMAT)


        }
    }

}*/
