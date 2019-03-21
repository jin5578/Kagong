package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import io.reactivex.Single


class AccompanyWriteRepositoryImpl(private val hostApi: HostApi) : AccompanyWriteRepository {
    override fun bringNickname(googlekey: String): Single<BringNicknameResult>
            = hostApi.bringNickname(googlekey)
}