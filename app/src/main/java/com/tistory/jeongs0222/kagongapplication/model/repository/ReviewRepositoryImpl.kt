package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


class ReviewRepositoryImpl(private val hostApi: HostApi): ReviewRepository {
    override fun locationReviewWrite(userkey: String, written_time: String, order: Int, content: String): Single<BasicResult>
        = hostApi.locationReviewWrite(userkey, written_time, order, content)
}