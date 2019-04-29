package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResponse
import io.reactivex.Single


interface ReviewRepository {
    fun locationReviewWrite(
        userkey: String,
        written_time: String,
        order: Int,
        content: String
    ): Single<BasicResult>

    fun bringReview(
        order: Int,
        sort: Int
    ): Single<BringLocationReviewResponse>
}