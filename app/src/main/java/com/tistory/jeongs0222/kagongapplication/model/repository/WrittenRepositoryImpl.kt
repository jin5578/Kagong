package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResponse
import io.reactivex.Single


class WrittenRepositoryImpl(private val hostApi: HostApi) : WrittenRepository {
    override fun writtenAccompany(userkey: String): Single<WrittenAccompanyResponse> = hostApi.writtenAccompany(userkey)

    override fun deleteAccompany(userkey: String, content: String, writtenTime: String): Single<BasicResult>
        = hostApi.deleteAccompany(userkey, content, writtenTime)

}