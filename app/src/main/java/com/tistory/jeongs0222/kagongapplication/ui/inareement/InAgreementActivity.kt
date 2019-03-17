package com.tistory.jeongs0222.kagongapplication.ui.inareement

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityInagreementBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.register.RegisterActivity
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class InAgreementActivity : BaseActivity<ActivityInagreementBinding>() {

    override val layoutResourceId: Int = R.layout.activity_inagreement

    private val inAgreementViewModel by viewModel<InAgreementViewModel>()

    private val intentProvider = IntentProviderImpl(this@InAgreementActivity) as IntentProvider
    private val  messageProvider = MessageProviderImpl(this@InAgreementActivity) as MessageProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inAgreementViewModel.nextClick.observe(this@InAgreementActivity, Observer {
            if(inAgreementViewModel.agreement.value!!)
                intentProvider.finishIntent(RegisterActivity::class.java)
            else
                messageProvider.toastMessage(applicationContext.getString(R.string.please_inagreement))
        })

        inAgreementViewModel.closeClick.observe(this@InAgreementActivity, Observer {
            messageProvider.snackbar(findViewById(R.id.entire_constraint), applicationContext.getString(R.string.application_exit), Snackbar.LENGTH_LONG)
        })

        viewDataBinding.iViewModel = inAgreementViewModel
        viewDataBinding.lifecycleOwner = this@InAgreementActivity
    }
}
