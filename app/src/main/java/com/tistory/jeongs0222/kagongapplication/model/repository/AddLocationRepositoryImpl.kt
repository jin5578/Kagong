package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResponse
import io.reactivex.Single


class AddLocationRepositoryImpl(private val hostApi: HostApi): AddLocationRepository {
    override fun findLocation(area: String, findlocation: String): Single<FindLocationResponse>
        = hostApi.bringLocation(area, findlocation)
}