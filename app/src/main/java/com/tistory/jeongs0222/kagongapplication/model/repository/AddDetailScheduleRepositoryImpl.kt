package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.deleteLocation.DeleteLocationResult
import com.tistory.jeongs0222.kagongapplication.model.host.deleteSchedule.DeleteScheduleResult
import io.reactivex.Single


class AddDetailScheduleRepositoryImpl(private val hostApi: HostApi): AddDetailScheduleRepository {
    override fun deleteSchedule(googlekey: String, area: String): Single<DeleteScheduleResult>
            = hostApi.deleteSchedule(googlekey, area)

    override fun bringDetailSchedule(googlekey: String, area: String): Single<BringDetailScheduleResponse>
            = hostApi.bringDetailSchedule(googlekey, area)

    override fun deleteLocation(googlekey: String, area: String, sort: String): Single<DeleteLocationResult>
            = hostApi.deleteLocation(googlekey, area, sort)
}