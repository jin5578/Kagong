package com.tistory.jeongs0222.kagongapplication.ui.setting

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivitySettingBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.termsofuse.PrivacyPolicyActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import kotlinx.android.synthetic.main.custom_alertdialog_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingActivity : BaseActivity<ActivitySettingBinding>() {

    override val layoutResourceId: Int = R.layout.activity_setting

    private val settingViewModel by viewModel<SettingViewModel>()

    private val dbHelperProvider = DBHelperProviderImpl(this@SettingActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@SettingActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@SettingActivity) as IntentProvider

    private lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingViewModel.bind(dbHelperProvider, messageProvider)

        settingViewModel.previousClick.observe(this@SettingActivity, Observer {
            finish()
        })

        settingViewModel.termsOfUseClick.observe(this@SettingActivity, Observer {
            intentProvider.intent(PrivacyPolicyActivity::class.java)
        })

        settingViewModel.withdrawalClick.observe(this@SettingActivity, Observer {
            val builder = AlertDialog.Builder(this@SettingActivity)
            val inflater = this.layoutInflater.inflate(R.layout.custom_alertdialog_layout, null)

            inflater.content.text = "삭제된 계정은 복구할 수 없습니다."

            builder.setView(inflater)

            inflater.findViewById<TextView>(R.id.cancel).setOnClickListener {
                alertDialog.dismiss()
            }

            inflater.findViewById<TextView>(R.id.check).setOnClickListener {
                alertDialog.dismiss()

                settingViewModel.deleteSQLite()
            }

            alertDialog = builder.create()

            alertDialog.show()
        })

        settingViewModel.appShutDownRequest.observe(this@SettingActivity, Observer {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        })

        viewDataBinding.sViewModel = settingViewModel
        viewDataBinding.lifecycleOwner = this@SettingActivity
    }

    override fun onBackPressed() {
        finish()
    }

}