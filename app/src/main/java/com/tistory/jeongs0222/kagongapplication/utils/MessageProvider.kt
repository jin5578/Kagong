package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.tistory.jeongs0222.kagongapplication.R


interface MessageProvider {
    fun toastMessage(message: String)

    fun snackbar(view: View, message: String, duration: Int)
}

class MessageProviderImpl(private val activity: Activity) : MessageProvider {

    override fun toastMessage(message: String) {
        val toastView =
            activity.layoutInflater.inflate(R.layout.toastmessage_layout, activity.findViewById(R.id.constraintLayout))

        val textView = toastView.findViewById<TextView>(R.id.textView)
        textView.text = message

        val toast = Toast(activity)
        toast.setGravity(Gravity.BOTTOM, 0, 260)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = toastView

        toast.show()
    }

    override fun snackbar(view: View, message: String, duration: Int) {
        val snackbar = Snackbar.make(view, message, duration)
        snackbar.setActionTextColor(Color.BLACK)

        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(activity.resources.getColor(R.color.colorPink))

        val snackbarTextView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        snackbarTextView.apply {
            setTextColor(Color.WHITE)
            textSize = 13F
            typeface = ResourcesCompat.getFont(activity, R.font.goyang)
        }

        val snackbarActionTextView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
        snackbarActionTextView.apply {
            setTextColor(Color.WHITE)
            textSize = 13F
            typeface = ResourcesCompat.getFont(activity, R.font.goyang)
        }

        snackbar.setAction("확인") {
            snackbar.dismiss()

            activity.finish()
        }

        snackbar.show()
    }
}