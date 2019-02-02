package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.nickname.NicknameResult
import io.reactivex.Single


interface RegisterRepository {
    fun nicknameValidate(nickname: String): Single<NicknameResult>
}