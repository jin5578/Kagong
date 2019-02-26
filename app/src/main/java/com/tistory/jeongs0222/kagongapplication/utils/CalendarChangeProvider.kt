package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddScheduleBinding


interface CalendarChangeProvider {
    fun calendarSelected(position: Int)

    fun calendarDeselected(position: Int)
}

class CalendarChangeProviderImpl(
    private val viewDataBinding: ActivityAddScheduleBinding,
    private val context: Context
) : CalendarChangeProvider {
    override fun calendarSelected(position: Int) {
        viewDataBinding.recyclerView.findViewHolderForLayoutPosition(position)
            .apply {
                if(this != null) {
                    this.itemView.background =
                            ContextCompat.getDrawable(context, R.drawable.day_selected_background)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.day_selected_background)
                }
            }
    }

    override fun calendarDeselected(position: Int) {
        viewDataBinding.recyclerView.findViewHolderForLayoutPosition(position)
            .apply {
                if(this != null) {
                    this.itemView.background =
                            ContextCompat.getDrawable(context, R.drawable.day_background)
                } else {
                    ContextCompat.getDrawable(context, R.drawable.day_background)
                }
        }
    }

}
