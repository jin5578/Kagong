package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Intent


interface IntentProvider {
    fun finishIntent(activityClass: Class<*>)

    fun intent(activityClass: Class<*>)
}

class IntentProviderImpl(private val activity: Activity): IntentProvider {
    override fun finishIntent(activityClass: Class<*>) {
        activity.startActivity(Intent(activity, activityClass))
        activity.finish()
    }

    override fun intent(activityClass: Class<*>) {
        activity.startActivity(Intent(activity, activityClass))
    }
}