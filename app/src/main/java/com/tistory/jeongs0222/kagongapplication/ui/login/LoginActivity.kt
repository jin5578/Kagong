package com.tistory.jeongs0222.kagongapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.kakao.auth.Session
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.main.MainActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity"

    override val layoutResourceId: Int = R.layout.activity_login

    private val lViewModel by viewModel<LoginViewModel>()

    private val messageProvider = MessageProviderImpl(this@LoginActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@LoginActivity) as IntentProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lViewModel.bind(
            NetworkCheckProviderImpl(this@LoginActivity),
            GoogleSignProviderImpl(this@LoginActivity),
            KakaoSignProviderImpl(this@LoginActivity),
            DBHelperProviderImpl(this@LoginActivity),
            messageProvider,
            intentProvider
        )

        //네트워크 활성화 상태 확인
        lViewModel.lNetworkStatus.observe(this@LoginActivity, Observer {
            if (it) {
                lViewModel.bbind()

                lViewModel.lValidateUser.observe(this@LoginActivity, Observer {
                    if(it) {
                        intentProvider.finishIntent(MainActivity::class.java)
                    }
                })
            } else {
                messageProvider.settingAlertDialog(0)
            }
        })

        viewDataBinding.lViewModel = lViewModel
        viewDataBinding.lifecycleOwner = this@LoginActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Google Login
        if (requestCode == 10) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    lViewModel.firebaseSignIn(account)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }

            return

            //Kakao Login
        } else if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            Log.d(TAG, "requestCode: $requestCode, resultCode: $resultCode")

            return
        }
    }

    override fun onResume() {
        super.onResume()

        lViewModel.networkCheck()
    }

    override fun onDestroy() {
        super.onDestroy()

        lViewModel.removeCallback()

        lViewModel.mGoogleSignInClient.signOut()
    }

    override fun onBackPressed() {
        this@LoginActivity.apply {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

}
