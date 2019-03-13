package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResponse
import io.reactivex.Single


interface AddLocationRepository {

    fun findLocation(area: String, findlocation: String): Single<FindLocationResponse>

    fun registerLocation(googlekey: String, area: String, location: String, sort: String): Single<BasicResult>

}