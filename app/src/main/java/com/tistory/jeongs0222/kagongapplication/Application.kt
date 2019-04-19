package com.tistory.jeongs0222.kagongapplication

import android.app.Application
import android.content.Context
import com.kakao.auth.*
import com.tistory.jeongs0222.kagongapplication.di.kagongModules
import org.koin.android.ext.android.startKoin


class Application: Application() {
    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = this.applicationContext
        KakaoSDK.init(KakaoSDKAdapter())        //Kakao Login
        startKoin(this, kagongModules)
    }

    //Kakao Login
    private inner class KakaoSDKAdapter : KakaoAdapter() {
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                }

                override fun isSecureMode(): Boolean {
                    return false
                }

                override fun getApprovalType(): ApprovalType? {
                    return ApprovalType.INDIVIDUAL
                }

                override fun isSaveFormData(): Boolean {
                    return true
                }
            }
        }
        

        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig { context }
        }
    }
}