package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringLocationDetail.BringLocationDetailResult
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

}