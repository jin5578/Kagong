package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.deleteSchedule.DeleteScheduleResult
import io.reactivex.Single


interface AddDetailScheduleRepository {

    fun deleteSchedule(googlekey: String, area: String): Single<DeleteScheduleResult>

}