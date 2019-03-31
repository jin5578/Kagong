package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.AccuWeatherApi
import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.accuweather.AccuWeatherResponse
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaImage.BringAreaImageResult
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import io.reactivex.Single


class AreaDetailRepositoryImpl(private val hostApi: HostApi, private val accuWeatherApi: AccuWeatherApi) : AreaDetailRepository {
    override fun validateSchedule(googlekey: String, area: String): Single<ValidateScheduleResult>
            = hostApi.validateSchedule(googlekey, area)

    override fun bringAreaDetail(area: String): Single<AreaInformationResponse>
            = hostApi.bringAreaInformation(area)

    override fun areaLikeValidate(googlekey: String, area: String): Single<BasicResult>
            = hostApi.areaLikeValidate(googlekey, area)

    override fun areaLikeClick(googlekey: String, area: String, status: Int): Single<BasicResult>
            = hostApi.areaLikeClick(googlekey, area, status)

    override fun areaForecast(location: String, apikey: String): Single<AccuWeatherResponse>
            = accuWeatherApi.areaForecast(location, apikey)

    override fun bringAreaImage(area: String): Single<BringAreaImageResult>
            = hostApi.bringAreaImage(area)
}