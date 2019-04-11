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

    private val loginViewModel by viewModel<LoginViewModel>()

    private val networkCheckProvider = NetworkCheckProviderImpl(this@LoginActivity) as NetworkCheckProvider
    private val googleSignProvider = GoogleSignProviderImpl(this@LoginActivity) as GoogleSignProvider
    private val kakaoSignProvider = KakaoSignProviderImpl(this@LoginActivity, this@LoginActivity) as KakaoSignProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@LoginActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@LoginActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@LoginActivity) as IntentProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.bind(networkCheckProvider, googleSignProvider, kakaoSignProvider, dbHelperProvider, messageProvider, intentProvider)

        //네트워크 활성화 상태 확인
        loginViewModel.networkStatus.observe(this@LoginActivity, Observer {
            if (it) {
                loginViewModel.bbind()

                loginViewModel.validateUser.observe(this@LoginActivity, Observer {
                    if(it) {
                        intentProvider.finishIntent(MainActivity::class.java)
                    }
                })
            } else {
                messageProvider.networkAlerDialog()
            }
        })

        viewDataBinding.lViewModel = loginViewModel
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
                    loginViewModel.firebaseSignIn(account)
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

        loginViewModel.networkCheck()
    }

    override fun onDestroy() {
        super.onDestroy()

        loginViewModel.removeCallback()

        loginViewModel.mGoogleSignInClient.signOut()
    }

    override fun onBackPressed() {
        this@LoginActivity.apply {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

}
