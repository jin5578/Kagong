package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Intent


interface IntentProvider {
    fun intent(activityClass: Class<*>)
}

class IntentProviderImpl(private val activity: Activity): IntentProvider {
    override fun intent(activityClass: Class<*>) {
        activity.startActivity(Intent(activity, activityClass))
    }
}