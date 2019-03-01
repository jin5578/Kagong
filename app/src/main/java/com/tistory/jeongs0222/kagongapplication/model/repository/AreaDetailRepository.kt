package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import io.reactivex.Single


interface AreaDetailRepository {

    fun validateSchedule(googlekey: String, area: String): Single<ValidateScheduleResult>

    fun bringAreaDetail(area: String): Single<AreaInformationResponse>

}