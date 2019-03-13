package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResponse
import io.reactivex.Single


interface AddDetailScheduleRepository {

    fun deleteSchedule(googlekey: String, area: String): Single<BasicResult>

    fun bringDetailSchedule(googlekey: String, area: String): Single<BringDetailScheduleResponse>

    fun deleteLocation(googlekey: String, area: String, sort: String): Single<BasicResult>

}