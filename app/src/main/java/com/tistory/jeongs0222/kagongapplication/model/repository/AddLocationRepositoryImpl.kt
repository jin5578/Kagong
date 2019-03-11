package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.registerLocation.RegisterLocationResult
import io.reactivex.Single


class AddLocationRepositoryImpl(private val hostApi: HostApi): AddLocationRepository {
    override fun findLocation(area: String, findlocation: String): Single<FindLocationResponse>
        = hostApi.bringLocation(area, findlocation)

    override fun registerLocation(
        googlekey: String,
        area: String,
        location: String,
        sort: String
    ): Single<RegisterLocationResult>
        = hostApi.registerLocation(googlekey, area, location, sort)
}