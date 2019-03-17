package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import io.reactivex.Single


class AddLocationRepositoryImpl(private val hostApi: HostApi): AddLocationRepository {
    override fun bringAreaLocation(
        area: String,
        sort: Int,
        findlocation: String
    ): Single<BringAreaLocationResponse>
            = hostApi.bringAreaLocation(area, sort, findlocation)

    override fun registerLocation(
        googlekey: String,
        area: String,
        location: String,
        sort: String
    ): Single<BasicResult>
        = hostApi.registerLocation(googlekey, area, location, sort)
}