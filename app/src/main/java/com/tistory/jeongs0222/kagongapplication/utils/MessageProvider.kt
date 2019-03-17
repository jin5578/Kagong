package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.adddetailschedule.AddDetailScheduleViewModel
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel


interface MessageProvider {
    fun toastMessage(message: String)

    fun snackbar(view: View, message: String, duration: Int)

    fun addDetailScheduleAlertDialog(viewModel: DisposableViewModel, area: String, position: String, sort: Int)

    fun addLocationAlertDialog()
}

class MessageProviderImpl(private val activity: Activity) : MessageProvider {

    private lateinit var alertDialog: AlertDialog

    override fun toastMessage(message: String) {
        val toastView =
            activity.layoutInflater.inflate(R.layout.custom_toastmessage_layout, activity.findViewById(R.id.constraintLayout))

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

        snackbar.setAction(view.context.getString(R.string.check)) {
            snackbar.dismiss()

            activity.finish()
        }

        snackbar.show()
    }

    override fun addDetailScheduleAlertDialog(viewModel: DisposableViewModel, area: String, position: String, sort: Int) {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater.inflate(R.layout.custom_alertdialog_layout, null)

        builder.setView(inflater)

        inflater.findViewById<TextView>(R.id.cancel).setOnClickListener {
            alertDialog.dismiss()
        }

        inflater.findViewById<TextView>(R.id.check).setOnClickListener {
            if(sort == 0) {
                (viewModel as AddDetailScheduleViewModel).deleteSchedule(area)
            } else {
                (viewModel as AddDetailScheduleViewModel).deleteLocation(area, position)
            }
        }

        alertDialog = builder.create()

        alertDialog.show()
    }

    override fun addLocationAlertDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater.inflate(R.layout.custom_alertdialog_layout, null)

        builder.setView(inflater)

        inflater.findViewById<TextView>(R.id.cancel).setOnClickListener {
            alertDialog.dismiss()
        }

        inflater.findViewById<TextView>(R.id.check).setOnClickListener {
            activity.finish()
        }

        alertDialog = builder.create()

        alertDialog.show()    }
}