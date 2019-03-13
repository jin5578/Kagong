package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


interface AddScheduleRepository {

    fun addSchedule(googlekey: String, area: String, date: String): Single<BasicResult>

}