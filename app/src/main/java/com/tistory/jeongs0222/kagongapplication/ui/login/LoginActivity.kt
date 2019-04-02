package com.tistory.jeongs0222.kagongapplication.ui.login

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.*
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.KakaoSDK
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityLoginBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val TAG = "LoginActivity"

    override val layoutResourceId: Int = R.layout.activity_login

    private val loginViewModel by viewModel<LoginViewModel>()

    private val networkCheckProvider = NetworkCheckProviderImpl(this@LoginActivity) as NetworkCheckProvider
    private val googleSignProvider = GoogleSignProviderImpl(this@LoginActivity) as GoogleSignProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@LoginActivity) as DBHelperProvider
    private val messageProvider = MessageProviderImpl(this@LoginActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@LoginActivity) as IntentProvider

    //Kakao Login
    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("onCreate", "onCreate")

        loginViewModel.bind(networkCheckProvider, googleSignProvider, dbHelperProvider, messageProvider, intentProvider)

        //네트워크 활성화 상태 확인
        loginViewModel.networkStatus.observe(this@LoginActivity, Observer {
            if (it) {
                loginViewModel.bbind()

                callback = SessionCallback()

                loginViewModel.googleLoginClick.observe(this, Observer {
                    loginViewModel.googleLogin()
                })

                loginViewModel.kakaoLoginClick.observe(this, Observer {

                    Session.getCurrentSession().addCallback(callback)
                    //Session.getCurrentSession().checkAndImplicitOpen()

                    if(!Session.getCurrentSession().checkAndImplicitOpen()) {
                        kakaoLoginClick()
                    }
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

    //Kakao Login
    private fun kakaoLoginClick() {
        val authTypes = getAuthTypes()

        if (authTypes.size == 1) {
            Log.e("test", "test2")
            Session.getCurrentSession().open(authTypes[0], this)
        } else {
            Log.e("test", "test3")
            val authItems = createAuthItemArray(authTypes)
            val adapter = createLoginAdapter(authItems)
            val dialog = createLoginDialog(authItems, adapter)
            dialog.show()
        }
    }

    //Kakao Login
    private fun getAuthTypes(): List<AuthType> {
        val availableAuthTypes = ArrayList<AuthType>()

        if (Session.getCurrentSession().authCodeManager.isTalkLoginAvailable) {
            availableAuthTypes.add(AuthType.KAKAO_TALK)
        }

        availableAuthTypes.add(AuthType.KAKAO_ACCOUNT)

        var authTypes: Array<AuthType>? = KakaoSDK.getAdapter().sessionConfig.authTypes
        if (authTypes == null || authTypes.isEmpty() || authTypes.size == 1 && authTypes[0] == AuthType.KAKAO_LOGIN_ALL) {
            authTypes = AuthType.values()
        }
        availableAuthTypes.retainAll(Arrays.asList(*authTypes))

        // 개발자가 설정한 것과 available 한 타입이 없다면 직접계정 입력이 뜨도록 한다.
        if (availableAuthTypes.size == 0) {
            availableAuthTypes.add(AuthType.KAKAO_ACCOUNT)
        }

        return availableAuthTypes
    }

    //Kakao Login
    private fun createAuthItemArray(authTypes: List<AuthType>): Array<Item> {
        val itemList = ArrayList<Item>()
        if (authTypes.contains(AuthType.KAKAO_TALK)) {
            itemList.add(
                Item(
                    com.kakao.usermgmt.R.string.com_kakao_kakaotalk_account,
                    com.kakao.usermgmt.R.drawable.talk,
                    com.kakao.usermgmt.R.string.com_kakao_kakaotalk_account_tts,
                    AuthType.KAKAO_TALK
                )
            )
        }
        if (authTypes.contains(AuthType.KAKAO_ACCOUNT)) {
            itemList.add(
                Item(
                    com.kakao.usermgmt.R.string.com_kakao_other_kakaoaccount,
                    com.kakao.usermgmt.R.drawable.account,
                    com.kakao.usermgmt.R.string.com_kakao_other_kakaoaccount_tts,
                    AuthType.KAKAO_ACCOUNT
                )
            )
        }

        return itemList.toTypedArray()
    }

    //Kakao Login
    private fun createLoginAdapter(authItems: Array<Item>): ListAdapter {
        /*
      가능한 auth type들을 유저에게 보여주기 위한 준비.
     */
        return object : ArrayAdapter<Item>(
            this,
            android.R.layout.select_dialog_item,
            android.R.id.text1, authItems
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                var convertView = convertView
                if (convertView == null) {
                    val inflater = context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    convertView = inflater.inflate(com.kakao.usermgmt.R.layout.layout_login_item, parent, false)
                }
                val imageView = convertView!!.findViewById<ImageView>(com.kakao.usermgmt.R.id.login_method_icon)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setImageDrawable(resources.getDrawable(authItems[position].icon!!, context.theme))
                } else {
                    imageView.setImageDrawable(resources.getDrawable(authItems[position].icon!!))
                }
                val textView = convertView.findViewById<TextView>(com.kakao.usermgmt.R.id.login_method_text)
                textView.setText(authItems[position].textId)
                return convertView
            }
        }
    }

    //Kakao Login
    private fun createLoginDialog(authItems: Array<Item>, adapter: ListAdapter): Dialog {
        val dialog = Dialog(this, com.kakao.usermgmt.R.style.LoginDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.kakao.usermgmt.R.layout.layout_login_dialog)
        if (dialog.window != null) {
            dialog.window!!.setGravity(Gravity.CENTER)
        }

        val listView = dialog.findViewById<ListView>(com.kakao.usermgmt.R.id.login_list_view)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val authType = authItems[position].authType
            openSession(authType)
            dialog.dismiss()
        }

        val closeButton = dialog.findViewById<Button>(com.kakao.usermgmt.R.id.login_close_button)
        closeButton.setOnClickListener(View.OnClickListener { dialog.dismiss() })
        return dialog
    }

    //Kakao Login
    private fun openSession(authType: AuthType) {
        Log.e("test", "test4")
        Session.getCurrentSession().open(authType, this@LoginActivity)
    }

    //Kakao Login
    private class Item internal constructor(
        internal val textId: Int,
        val icon: Int?,
        internal val contentDescId: Int,
        internal val authType: AuthType
    )

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
        } else if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
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

        if(Session.getCurrentSession().isOpened) {
            Session.getCurrentSession().removeCallback(callback)
        }

        //Google도 넣어줘야한다.
    }

    private inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            messageProvider.toastMessage("카카오 로그인에 실패하였습니다.")
            if (exception != null) {
                Logger.e(exception)
            }
        }
        override fun onSessionOpened() {
            if(Session.getCurrentSession().tokenInfo != null) {
                messageProvider.toastMessage("카카오 로그인에 성공하였습니다.")

                requestMe()
            }
        }
    }

    private fun requestMe() {
        UserManagement.getInstance().me(object: MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                loginViewModel.keyCheck(result!!.id.toString())
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                if(errorResult != null) {
                    Log.e(TAG, errorResult.toString())
                }
            }
        })
    }
}
