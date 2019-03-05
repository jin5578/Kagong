package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.deleteSchedule.DeleteScheduleResult
import io.reactivex.Single


class AddDetailScheduleRepositoryImpl(private val hostApi: HostApi): AddDetailScheduleRepository {
    override fun deleteSchedule(googlekey: String, area: String): Single<DeleteScheduleResult>
            = hostApi.deleteSchedule(googlekey, area)
}