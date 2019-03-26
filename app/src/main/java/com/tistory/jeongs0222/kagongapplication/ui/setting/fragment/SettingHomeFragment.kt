package com.tistory.jeongs0222.kagongapplication.ui.setting.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSettingHomeBinding
import com.tistory.jeongs0222.kagongapplication.ui.setting.SettingViewModel
import kotlinx.android.synthetic.main.custom_alertdialog_layout.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SettingHomeFragment: Fragment() {

    private lateinit var binding: FragmentSettingHomeBinding

    private val settingViewModel by sharedViewModel<SettingViewModel>()

    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingHomeBinding.inflate(inflater, container, false).apply {
            sViewModel = settingViewModel
            lifecycleOwner = this@SettingHomeFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingViewModel.withdrawalClick.observe(this@SettingHomeFragment, Observer {
            val builder = AlertDialog.Builder(this.context)
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
    }
}