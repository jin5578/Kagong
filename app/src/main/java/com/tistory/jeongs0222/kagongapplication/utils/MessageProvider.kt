package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.tistory.jeongs0222.kagongapplication.BuildConfig
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.adddetailschedule.AddDetailScheduleViewModel
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel


interface MessageProvider {
    fun toastMessage(message: String)

    fun snackbar(view: View, message: String, duration: Int)

    fun settingAlertDialog(sort: Int)

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

            activity.apply {
                moveTaskToBack(true)
                finishAffinity()
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        }

        snackbar.show()
    }

    override fun settingAlertDialog(sort: Int) {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater.inflate(R.layout.layout_setting_alert, null)

        // 업데이트 AlertDialog인 경우
        if(sort == 2)
            inflater.findViewById<TextView>(R.id.setting).text = activity.resources.getString(R.string.update_right_now)


        inflater.findViewById<TextView>(R.id.content).apply {
            text = when(sort) {
                0 -> activity.resources.getString(R.string.network_disabled)

                1 -> activity.resources.getString(R.string.permission_disabled)

                else -> activity.resources.getString(R.string.application_disabled)
            }
        }

        builder.setView(inflater)

        inflater.findViewById<TextView>(R.id.setting).setOnClickListener {

            val intent = when(sort) {
                0 -> Intent(Settings.ACTION_WIFI_SETTINGS)

                1 -> Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + activity.packageName))

                else -> {
                    val uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)

                    Intent(Intent.ACTION_VIEW, uri).addFlags(
                        Intent.FLAG_ACTIVITY_NO_HISTORY or
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                    )
                }
            }

            activity.startActivity(intent)

            // 업데이트 AlertDialog인 경우 누르면 어플이케이션이 완전히 종료되도록
            if(sort == 2) {
                activity.apply {
                    moveTaskToBack(true)
                    finishAffinity()
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            }

            alertDialog.dismiss()
        }

        builder.setCancelable(false)

        alertDialog = builder.create()

        alertDialog.show()
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

        alertDialog.show()
    }

}