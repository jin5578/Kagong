package com.tistory.jeongs0222.kagongapplication.api

import com.tistory.jeongs0222.kagongapplication.model.accuweather.AccuWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AccuWeatherApi {

    @GET("{locationKey}")
    fun areaForecast(@Path("locationKey") location: String,
                     @Query("apikey") apikey: String): Single<AccuWeatherResponse>

}