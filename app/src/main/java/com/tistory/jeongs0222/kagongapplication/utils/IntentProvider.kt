package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri


interface IntentProvider {
    fun finishIntent(activityClass: Class<*>)

    fun finishPutTwoExtraIntent(activityClass: Class<*>, value1: String, value2: String)

    fun intent(activityClass: Class<*>)

    fun intentPutExtra(activityClass: Class<*>, area: String)

    fun intentPutTwoExtra(activityClass: Class<*>, area: String, order: String)

    fun intentActionView(variable: String)

    fun intentFinish()
}

class IntentProviderImpl(private val activity: Activity): IntentProvider {
    override fun finishIntent(activityClass: Class<*>) {
        activity.startActivity(Intent(activity, activityClass))
        activity.finish()
    }

    override fun finishPutTwoExtraIntent(activityClass: Class<*>, value1: String, value2: String) {
        val intent = Intent(activity, activityClass)
        intent.putExtra("value1", value1)
        intent.putExtra("value2", value2)

        activity.startActivity(intent)

        activity.finish()
    }

    override fun intent(activityClass: Class<*>) {
        activity.startActivity(Intent(activity, activityClass))
    }

    override fun intentPutExtra(activityClass: Class<*>, area: String) {
        val intent = Intent(activity, activityClass)
        intent.putExtra("area", area)

        activity.startActivity(intent)
    }

    override fun intentPutTwoExtra(activityClass: Class<*>, area: String, order: String) {
        val intent = Intent(activity, activityClass)
        intent.putExtra("area", area)
        intent.putExtra("order", order)

        activity.startActivity(intent)
    }

    override fun intentActionView(variable: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(variable))

        activity.startActivity(intent)
    }

    override fun intentFinish() {
        activity.finish()
    }
}