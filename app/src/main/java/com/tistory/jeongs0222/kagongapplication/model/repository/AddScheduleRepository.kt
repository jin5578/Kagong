package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.addSchedule.AddScheduleResult
import io.reactivex.Single


interface AddScheduleRepository {

    fun addSchedule(googlekey: String, area: String, date: String): Single<AddScheduleResult>

}