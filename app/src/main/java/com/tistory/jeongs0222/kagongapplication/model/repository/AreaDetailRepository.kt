package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.accuweather.AccuWeatherResponse
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import io.reactivex.Single


interface AreaDetailRepository {

    fun validateSchedule(googlekey: String, area: String): Single<ValidateScheduleResult>

    fun bringAreaDetail(area: String): Single<AreaInformationResponse>

    fun areaLikeValidate(googlekey: String, area: String): Single<BasicResult>

    fun areaLikeClick(googlekey: String, area: String, status: Int): Single<BasicResult>

    fun areaForecast(location: String, apikey: String): Single<AccuWeatherResponse>
}