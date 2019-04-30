package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringLocationDetail.BringLocationDetailResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringOperatingTime.BringOperatingTimeResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResponse
import io.reactivex.Single


interface LocationDetailRepository {

    fun bringLocationDetail(
        order: Int
    ): Single<BringLocationDetailResult>

    fun locationLikeClick(
        userkey: String,
        order: Int,
        status: Int
    ): Single<BasicResult>

    fun locationLikeValidate(
        userkey: String,
        order: Int
    ): Single<BasicResult>

    fun bringLocationReview(
        order: Int,
        sort: Int
    ): Single<BringLocationReviewResponse>

    fun bringOperatingTime(
        order: Int
    ): Single<BringOperatingTimeResponse>

}