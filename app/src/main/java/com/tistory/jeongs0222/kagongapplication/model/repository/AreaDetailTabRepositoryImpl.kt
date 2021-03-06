package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import io.reactivex.Single


class AreaDetailTabRepositoryImpl(private val hostApi: HostApi): AreaDetailTabRepository {
    override fun bringAreaLocation(area: String, sort: Int, findlocation: String): Single<BringAreaLocationResponse>
            = hostApi.bringAreaLocation(area, sort, findlocation)

    override fun bringAreaGoodPlace(area: String, sort: Int, findGoodPlace: String): Single<BringAreaGoodPlaceResponse>
            = hostApi.bringAreaGoodPlace(area, sort, findGoodPlace)

    override fun bringAccompany(area: String, tab: Int): Single<BringAccompanyResponse>
            = hostApi.bringAccompany(area, tab)
}