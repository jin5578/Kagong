package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.nickname.NicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.register.RegisterResult
import io.reactivex.Single


interface RegisterRepository {
    fun nicknameValidate(nickname: String): Single<NicknameResult>

    fun register(userkey: String, token: String, nickname: String, sex: String, age: String): Single<RegisterResult>
}