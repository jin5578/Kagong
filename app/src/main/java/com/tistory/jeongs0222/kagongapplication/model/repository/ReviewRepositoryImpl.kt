package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResponse
import io.reactivex.Single


class ReviewRepositoryImpl(private val hostApi: HostApi): ReviewRepository {
    override fun locationReviewWrite(userkey: String, written_time: String, order: Int, content: String): Single<BasicResult>
        = hostApi.locationReviewWrite(userkey, written_time, order, content)

    override fun bringReview(order: Int, sort: Int): Single<BringLocationReviewResponse>
        = hostApi.bringReview(order, sort)
}