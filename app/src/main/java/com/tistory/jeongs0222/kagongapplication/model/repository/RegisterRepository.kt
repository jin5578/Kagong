package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


interface RegisterRepository {
    fun nicknameValidate(nickname: String): Single<BasicResult>

    fun register(userkey: String, token: String, nickname: String, sex: String, age: String): Single<BasicResult>
}