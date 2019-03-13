package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import io.reactivex.Single


class AreaDetailRepositoryImpl(private val hostApi: HostApi) : AreaDetailRepository {
    override fun validateSchedule(googlekey: String, area: String): Single<ValidateScheduleResult>
            = hostApi.validateSchedule(googlekey, area)

    override fun bringAreaDetail(area: String): Single<AreaInformationResponse>
            = hostApi.bringAreaInformation(area)

    override fun bringAreaLocation(area: String): Single<BringAreaLocationResponse>
            = hostApi.bringAreaLocation(area)
}