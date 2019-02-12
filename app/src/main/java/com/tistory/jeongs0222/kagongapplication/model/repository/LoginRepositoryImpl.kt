package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.keyCheck.KeyCheckResult
import io.reactivex.Single


class LoginRepositoryImpl(private val hostApi: HostApi) : LoginRepository {
    override fun keyCheck(googlekey: String): Single<KeyCheckResult> = hostApi.keyCheck(googlekey)
}