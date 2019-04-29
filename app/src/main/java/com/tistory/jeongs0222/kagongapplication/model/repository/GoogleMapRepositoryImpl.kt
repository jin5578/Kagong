package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.locationMap.LocationMapResult
import io.reactivex.Single


class GoogleMapRepositoryImpl(private val hostApi: HostApi): GoogleMapRepository {
    override fun locationMap(order: Int): Single<LocationMapResult>
        = hostApi.locationMap(order)
}