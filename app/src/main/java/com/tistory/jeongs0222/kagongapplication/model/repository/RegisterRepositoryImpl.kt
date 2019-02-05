package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.NicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.register.RegisterResult
import io.reactivex.Single


class RegisterRepositoryImpl(private val hostApi: HostApi) : RegisterRepository {

    override fun nicknameValidate(nickname: String): Single<NicknameResult> = hostApi.nicknameValidate(nickname)

    override fun register(userkey: String, token: String, nickname: String, sex: String, age: String): Single<RegisterResult> = hostApi.register(userkey, token, nickname, sex, age)

}