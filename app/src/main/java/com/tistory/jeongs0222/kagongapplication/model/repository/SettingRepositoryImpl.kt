package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


class SettingRepositoryImpl(private val hostApi: HostApi): SettingRepository {
    override fun deleteUser(googlekey: String): Single<BasicResult>
        = hostApi.deleteUser(googlekey)
}