package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.NicknameResult
import io.reactivex.Single


class RegisterRepositoryImpl(private val hostApi: HostApi) : RegisterRepository {

    override fun nicknameValidate(nickname: String): Single<NicknameResult> {
        return hostApi.nicknameValidate(nickname)
    }
}