package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


class AddScheduleRepositoryImpl(private val hostApi: HostApi) : AddScheduleRepository {
    override fun addSchedule(googlekey: String, area: String, date: String): Single<BasicResult> =
        hostApi.addSchedule(googlekey, area, date)
}