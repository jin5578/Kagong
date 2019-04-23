package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResponse
import io.reactivex.Single


interface WrittenRepository {
    fun writtenAccompany(userkey: String): Single<WrittenAccompanyResponse>

    fun deleteAccompany(userkey: String, content: String, writtenTime: String): Single<BasicResult>
}