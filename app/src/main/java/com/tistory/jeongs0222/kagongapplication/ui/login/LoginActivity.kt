package com.tistory.jeongs0222.kagongapplication.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity"

    override val layoutResourceId: Int = R.layout.activity_login

    private val loginViewModel by viewModel<LoginViewModel>()

    private val networkCheckProvider = NetworkCheckProviderImpl(this@LoginActivity) as NetworkCheckProvider
    private val googleSignProvider = GoogleSignProviderImpl(this@LoginActivity) as GoogleSignProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@LoginActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@LoginActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@LoginActivity) as IntentProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("onCreate", "onCreate")

        loginViewModel.bind(networkCheckProvider, googleSignProvider, dbHelperProvider, messageProvider, intentProvider)

        loginViewModel.networkStatus.observe(this@LoginActivity, Observer {
            if (it) {
                loginViewModel.bbind()

                loginViewModel.googleLogin.observe(this, Observer {
                    loginViewModel.googleLogin()
                })
            } else {
                AlertDialog.Builder(this@LoginActivity)
                    .apply {
                        setTitle("알림")
                        setMessage("인터넷 연결이 끊어졌습니다. \n다시 시도해보세요.")
                        setNeutralButton("설정") { dialogInterface, which ->
                            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)

                            startActivity(intent)
                        }
                        setCancelable(false)
                    }
                    .create()
                    .show()
            }
        })

        viewDataBinding.lViewModel = loginViewModel
        viewDataBinding.lifecycleOwner = this@LoginActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

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
        }
    }

    override fun onResume() {
        super.onResume()

        loginViewModel.networkCheck()
    }


}
