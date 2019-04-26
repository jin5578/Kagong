package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringLocationDetail.BringLocationDetailResult
import io.reactivex.Single


class LocationDetailRepositoryImpl(private val hostApi: HostApi) : LocationDetailRepository {
    override fun bringLocationDetail(order: Int): Single<BringLocationDetailResult> = hostApi.bringLocationDetail(order)

    override fun locationLikeClick(userkey: String, order: Int, status: Int): Single<BasicResult> =
        hostApi.locationLikeClick(userkey, order, status)

    override fun locationLikeValidate(userkey: String, order: Int)
        = hostApi.locationLikeValidate(userkey, order)


}