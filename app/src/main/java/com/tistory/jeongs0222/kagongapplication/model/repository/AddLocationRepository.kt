package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import io.reactivex.Single


interface AddLocationRepository {

    fun bringAreaLocation(
        area: String,
        sort: Int,
        findlocation: String
    ): Single<BringAreaLocationResponse>

    fun registerLocation(
        googlekey: String,
        area: String,
        location: String,
        sort: String
    ): Single<BasicResult>

}