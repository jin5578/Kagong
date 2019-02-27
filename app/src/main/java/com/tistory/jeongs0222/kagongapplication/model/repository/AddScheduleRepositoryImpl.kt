package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.addSchedule.AddScheduleResult
import io.reactivex.Single


class AddScheduleRepositoryImpl(private val hostApi: HostApi) : AddScheduleRepository {
    override fun addSchedule(googlekey: String, area: String, date: String): Single<AddScheduleResult> =
        hostApi.addSchedule(googlekey, area, date)
}