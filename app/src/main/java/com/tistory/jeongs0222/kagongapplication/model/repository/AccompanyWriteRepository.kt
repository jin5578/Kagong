package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNickname.BringNicknameResult
import io.reactivex.Single


interface AccompanyWriteRepository {
    fun bringNickname(googlekey: String): Single<BringNicknameResult>

    fun accompanyWrite(
        area: String,
        sort: Int,
        googlekey: String,
        content: String,
        writtenTime: String,
        meetingDate: String,
        link: String): Single<BasicResult>
}