package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResponse
import io.reactivex.Single


class AddDetailScheduleRepositoryImpl(private val hostApi: HostApi): AddDetailScheduleRepository {
    override fun deleteSchedule(googlekey: String, area: String): Single<BasicResult>
            = hostApi.deleteSchedule(googlekey, area)

    override fun bringDetailSchedule(googlekey: String, area: String): Single<BringDetailScheduleResponse>
            = hostApi.bringDetailSchedule(googlekey, area)

    override fun deleteLocation(googlekey: String, area: String, sort: String): Single<BasicResult>
            = hostApi.deleteLocation(googlekey, area, sort)
}