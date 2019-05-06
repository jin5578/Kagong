package com.tistory.jeongs0222.kagongapplication.ui.privacypolicy

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityPrivacyPolicyBinding
           import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class PrivacyPolicyActivity: BaseActivity<ActivityPrivacyPolicyBinding>() {

    override val layoutResourceId: Int = R.layout.activity_privacy_policy

    private val termsOfUseViewModel by viewModel<PrivacyPolicyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        termsOfUseViewModel.previousClick.observe(this@PrivacyPolicyActivity, Observer {
            finish()
        })

        viewDataBinding.webview.apply {
            webViewClient = WebViewClient()
            loadUrl("https://jin5578.cafe24.com/KAGONG/Serendipity_termsofUse.html")
        }
        viewDataBinding.webview.webViewClient = WebViewClient()


        viewDataBinding.toViewModel = termsOfUseViewModel
        viewDataBinding.lifecycleOwner = this@PrivacyPolicyActivity
    }

    override fun onBackPressed() {
        finish()
    }

}