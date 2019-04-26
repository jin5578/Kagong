package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.tistory.jeongs0222.kagongapplication.BuildConfig


interface IntentProvider {
    fun finishIntent(activityClass: Class<*>)


    fun finishPutTwoExtraIntent(activityClass: Class<*>, value1: String, value2: String)

    fun intent(activityClass: Class<*>)

    fun intentPutExtra(activityClass: Class<*>, area: String)

    fun iIntent(activityClass: Class<*>, value1: Int)

    fun intentPutTwoExtra(activityClass: Class<*>, area: String, order: String)

    fun intentActionView(variable: String)

    fun intentFinish()

    fun intentGallery()

    fun intentReview()
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

    override fun iIntent(activityClass: Class<*>, value1: Int) {
        val intent = Intent(activity, activityClass)
        intent.putExtra("value1", value1)

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

    override fun intentGallery() {
        val PICK_FROM_GALLERY = 111

        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE

        activity.startActivityForResult(intent, PICK_FROM_GALLERY)
    }

    override fun intentReview() {
        val uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)

        var intent = Intent(Intent.ACTION_VIEW, uri)

        intent.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )

        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        } else {
            intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID))
            if (intent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(intent)
            }
        }
    }
}