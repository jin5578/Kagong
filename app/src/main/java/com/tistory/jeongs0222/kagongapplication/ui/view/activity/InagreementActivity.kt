package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityInagreementBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.InagreementViewModel
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProviderImpl


class InagreementActivity : BaseActivity<ActivityInagreementBinding>() {

    override val layoutResourceId: Int = R.layout.activity_inagreement

    private val  messageProvider = MessageProviderImpl(this@InagreementActivity) as MessageProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inagreementViewModel = InagreementViewModel()

        inagreementViewModel.nextClick.observe(this@InagreementActivity, Observer {
            if(inagreementViewModel.agreement.value!!)
                startActivity(Intent(this@InagreementActivity, RegisterActivity::class.java))
            else
                messageProvider.toastMessage("약관동의를 해주세요")
        })

        inagreementViewModel.closeClick.observe(this@InagreementActivity, Observer {
            messageProvider.snackbar(findViewById(R.id.entire_constraint), "앱을 종료하시겠습니까?", Snackbar.LENGTH_LONG)
        })

        viewDataBinding.iViewModel = inagreementViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
