package com.tistory.jeongs0222.kagongapplication.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger
import com.tistory.jeongs0222.kagongapplication.model.repository.LoginRepository
import com.tistory.jeongs0222.kagongapplication.ui.inareement.InAgreementActivity
import com.tistory.jeongs0222.kagongapplication.ui.main.MainActivity
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class  LoginViewModel(private val loginRepository: LoginRepository) : DisposableViewModel() {

    private val _lValidateUser = MutableLiveData<Boolean>()
    val lValidateUser: LiveData<Boolean>
        get() = _lValidateUser

    private val _lNetworkStatue = MutableLiveData<Boolean>()
    val lNetworkStatus: LiveData<Boolean>
        get() = _lNetworkStatue


    private val TAG = "LoginViewModel"

    private lateinit var networkCheckProvider: NetworkCheckProvider
    private lateinit var googleSignProvider: GoogleSignProvider
    private lateinit var kakaoSignProvider: KakaoSignProvider
    private lateinit var dbHelperProvider: DBHelperProvider
    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider

    private lateinit var callback: SessionCallback

    private lateinit var user: String

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient

    lateinit var loginMethod: String


    fun bind(networkCheckProvider: NetworkCheckProvider, googleSignProvider: GoogleSignProvider, kakaoSignProvider: KakaoSignProvider, dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider, intentProvider: IntentProvider) {
        this.networkCheckProvider = networkCheckProvider
        this.googleSignProvider = googleSignProvider
        this.kakaoSignProvider = kakaoSignProvider
        this.dbHelperProvider = dbHelperProvider
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider
    }

    fun bbind() {
        validateUserCheck()

        callback = SessionCallback()

        gso = googleSignProvider.getGoogleSignInOptions()

        mGoogleSignInClient = googleSignProvider.getGoogleSignInClient(gso)
    }

    private fun validateUserCheck() {
        _lValidateUser.value = dbHelperProvider.getDBHelper().getUserKey().isNotEmpty()
    }

    fun networkCheck() {
        _lNetworkStatue.value = networkCheckProvider.isNetworkConeected()
    }

    fun googleLoginClickEvent() {
        loginMethod = "Google"

        googleLogin()
    }

    fun kakaoLoginClickEvent() {
        loginMethod = "Kakao"

        kakaoLogin()
    }

    private fun googleLogin() {
        googleSignProvider.googleSignIn(mGoogleSignInClient)
    }

    fun firebaseSignIn(acct: GoogleSignInAccount) {
        googleSignProvider.firebaseSignIn(acct).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                user = FirebaseAuth.getInstance().currentUser!!.uid

                keyCheck(user)

                messageProvider.toastMessage("구글 로그인에 성공하였습니다")
            } else {
                messageProvider.toastMessage("구글 로그인에 실패하였습니다.")
            }
        }
    }



    @SuppressLint("CheckResult")
    fun keyCheck(userkey: String) {
        loginRepository.keyCheck(userkey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                when {
                    it.value == 0 -> {
                        intentProvider.finishPutTwoExtraIntent(InAgreementActivity::class.java, userkey, loginMethod)
                    }

                    it.value == 1 -> {
                        intentProvider.finishPutTwoExtraIntent(InAgreementActivity::class.java, userkey, loginMethod)
                    }

                    it.value == 2 -> {
                        intentProvider.finishIntent(MainActivity::class.java)

                        dbHelperProvider.getDBHelper().insertUser(userkey)
                    }
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            messageProvider.toastMessage("카카오 로그인에 실패하였습니다.")
            if (exception != null) {
                Logger.e(exception)
            }
        }

        override fun onSessionOpened() {
            if (Session.getCurrentSession().tokenInfo != null) {
                messageProvider.toastMessage("카카오 로그인에 성공하였습니다.")

                requestMe()
            }
        }
    }

    private fun kakaoLogin() {
        Session.getCurrentSession().addCallback(callback)

        if(!Session.getCurrentSession().checkAndImplicitOpen()) {
            kakaoSignProvider.kakaoLogin()
        }
    }

    private fun requestMe() {
        UserManagement.getInstance().me(object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {
                keyCheck(result!!.id.toString())
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                if (errorResult != null)
                    Log.e(TAG, errorResult.toString())

            }
        })
    }

    fun removeCallback() {
        if(Session.getCurrentSession().isOpened)
            Session.getCurrentSession().removeCallback(callback)
    }
}