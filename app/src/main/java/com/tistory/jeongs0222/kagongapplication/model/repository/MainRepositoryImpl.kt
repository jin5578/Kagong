package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResponse
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import io.reactivex.Single


class MainRepositoryImpl(private val hostApi: HostApi): MainRepository {
    override fun bringNickname(googlekey: String): Single<BringNicknameResult> = hostApi.bringNickname(googlekey)

    override fun bringHistory(googlekey: String): Single<AreaSearchResponse> = hostApi.bringHistory(googlekey)
}