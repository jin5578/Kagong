package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import io.reactivex.Single


interface AreaDetailTabRepository {

    fun bringAreaLocation(
        area: String
    ): Single<BringAreaLocationResponse>

    fun bringAreaGoodPlace(
        area: String
    ): Single<BringAreaGoodPlaceResponse>

}