package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import io.reactivex.Single


interface AreaDetailTabRepository {

    fun bringAreaLocation(
        area: String,
        sort: Int,
        findlocation: String
    ): Single<BringAreaLocationResponse>

    fun bringAreaGoodPlace(
        area: String,
        sort: Int,
        findGoodPlace: String
    ): Single<BringAreaGoodPlaceResponse>

}