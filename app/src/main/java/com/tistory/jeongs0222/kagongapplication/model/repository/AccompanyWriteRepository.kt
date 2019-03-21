package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import io.reactivex.Single


interface AccompanyWriteRepository {
    fun bringNickname(googlekey: String): Single<BringNicknameResult>

    fun accompanyWrite(
        area: String,
        sort: Int,
        googlekey: String,
        title: String,
        content: String,
        writtenTime: String,
        meetingDate: String): Single<BasicResult>
}