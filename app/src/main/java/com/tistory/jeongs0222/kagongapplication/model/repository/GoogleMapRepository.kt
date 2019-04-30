package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.locationMap.LocationMapResult
import io.reactivex.Single


interface GoogleMapRepository {
    fun locationMap(order: Int): Single<LocationMapResult>
}