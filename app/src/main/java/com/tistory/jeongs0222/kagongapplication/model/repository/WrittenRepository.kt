package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResponse
import io.reactivex.Single


interface WrittenRepository {
    fun writtenAccompany(userkey: String): Single<WrittenAccompanyResponse>
}