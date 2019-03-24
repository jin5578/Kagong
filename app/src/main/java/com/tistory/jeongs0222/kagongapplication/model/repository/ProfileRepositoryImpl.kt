package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNicknameAndIntro.BringNicknameAndIntroResult
import io.reactivex.Single


class ProfileRepositoryImpl(private val hostApi: HostApi): ProfileRepository {
    override fun bringNicknameAndIntro(googlekey: String): Single<BringNicknameAndIntroResult>
            = hostApi.bringNicknameAndIntro(googlekey)

    override fun nicknameValidate(nickname: String): Single<BasicResult>
            = hostApi.nicknameValidate(nickname)

    override fun updateProfile(googlekey: String, nickname: String, introduce: String): Single<BasicResult>
            = hostApi.updateProfile(googlekey, nickname, introduce)
}