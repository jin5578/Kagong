package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


class LoginRepositoryImpl(private val hostApi: HostApi) : LoginRepository {
    override fun keyCheck(googlekey: String): Single<BasicResult> = hostApi.keyCheck(googlekey)
}