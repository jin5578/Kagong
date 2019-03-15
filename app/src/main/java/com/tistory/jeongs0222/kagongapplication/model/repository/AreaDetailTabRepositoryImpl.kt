package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import io.reactivex.Single


class AreaDetailTabRepositoryImpl(private val hostApi: HostApi): AreaDetailTabRepository {
    override fun bringAreaLocation(area: String): Single<BringAreaLocationResponse>
            = hostApi.bringAreaLocation(area)

    override fun bringAreaGoodPlace(area: String): Single<BringAreaGoodPlaceResponse>
            = hostApi.bringAreaGoodPlace(area)
}