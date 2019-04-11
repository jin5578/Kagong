package com.tistory.jeongs0222.kagongapplication.ui.login

import android.annotation.SuppressLint
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.tistory.jeongs0222.kagongapplication.model.repository.LoginRepository
import com.tistory.jeongs0222.kagongapplication.ui.inareement.InAgreementActivity
import com.tistory.jeongs0222.kagongapplication.ui.main.MainActivity
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class  LoginViewModel(private val loginRepository: LoginRepository) : DisposableViewModel() {

    private val _googleLoginClick = SingleLiveEvent<ConstraintLayout>()
    val googleLoginClick: LiveData<ConstraintLayout>
        get() = _googleLoginClick

    private val _kakaoLoginClick = SingleLiveEvent<Any>()
    val kakaoLoginClick: LiveData<Any>
        get() = _kakaoLoginClick

    private val _networkStatue = MutableLiveData<Boolean>()
    val networkStatus: LiveData<Boolean>
        get() = _networkStatue

    private val _validateUser = MutableLiveData<Boolean>()
    val validateUser: LiveData<Boolean>
        get() = _validateUser

    private val TAG = "LoginViewModel"

    private lateinit var networkCheckProvider: NetworkCheckProvider
    private lateinit var googleSignProvider: GoogleSignProvider
    private lateinit var dbHelperProvider: DBHelperProvider
    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleSignInClient: GoogleSignInClient

    private lateinit var user: String

    lateinit var loginMethod: String

    fun bind(networkCheckProvider: NetworkCheckProvider, googleSignProvider: GoogleSignProvider, dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider, intentProvider: IntentProvider) {
        this.networkCheckProvider = networkCheckProvider
        this.googleSignProvider = googleSignProvider
        this.dbHelperProvider = dbHelperProvider
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider
    }

    fun bbind() {
        validateUserCheck()

        gso = googleSignProvider.getGoogleSignInOptions()

        mGoogleSignInClient = googleSignProvider.getGoogleSignInClient(gso)
    }

    fun networkCheck() {
        _networkStatue.value = networkCheckProvider.isNetworkConeected()
    }

    fun googleLoginClickEvent() {
        loginMethod = "Google"

        _googleLoginClick.call()
    }

    fun kakaoLoginClickEvent() {
        loginMethod = "Kakao"

        _kakaoLoginClick.call()
    }

    fun googleLogin() {
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

    private fun validateUserCheck() {
        _validateUser.value = dbHelperProvider.getDBHelper().getUserKey().isNotEmpty()
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

}