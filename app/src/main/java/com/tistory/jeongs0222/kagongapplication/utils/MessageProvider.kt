package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.tistory.jeongs0222.kagongapplication.R


interface MessageProvider {
    fun toastMessage(message: String)

    fun snackbar()
}

class MessageProviderImpl(private val activity: Activity): MessageProvider {

    override fun toastMessage(message: String) {
        val toastView = activity.layoutInflater.inflate(R.layout.toastmessage_layout, activity.findViewById(R.id.constraintLayout))

        val textView = toastView.findViewById<TextView>(R.id.textView)
        textView.text = message

        val toast = Toast(activity)
        toast.setGravity(Gravity.BOTTOM, 0, 260)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = toastView

        toast.show()


        /*val toastView = activity.layoutInflater.inflate(R.layout.toastmessage_layout, null)

        val toast = Toast(activity)
        toast.view = toastView
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.setText(message)
        toast.show()*/
        //Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun snackbar() {

    }
}