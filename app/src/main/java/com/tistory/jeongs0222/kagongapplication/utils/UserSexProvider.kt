package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.tistory.jeongs0222.kagongapplication.R


interface UserSexProvider {
    fun sexChange(sex: String)
}

class UserSexProviderImpl(private val activity: FragmentActivity) : UserSexProvider {
    override fun sexChange(sex: String) {
        var position1 = R.id.female
        var position2 = R.id.male

        if(sex == "female") {
            position1 = R.id.female
            position2 = R.id.male
        } else {
            position1 = R.id.male
            position2 = R.id.female
        }

        activity.findViewById<TextView>(position1).apply {
            setTextColor(activity.resources.getColor(R.color.colorWhite))
            background = activity.resources.getDrawable(R.drawable.selected_background)
        }

        activity.findViewById<TextView>(position2).apply {
            setTextColor(activity.resources.getColor(R.color.colorGray3))
            background = activity.resources.getDrawable(R.drawable.edittext_background)
        }

    }
}