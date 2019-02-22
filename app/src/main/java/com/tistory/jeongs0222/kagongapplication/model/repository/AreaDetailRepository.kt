package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.accompanylist.AccompanyListResponse
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import io.reactivex.Single


interface AreaDetailRepository {

    fun bringAreaDetail(area: String): Single<AreaInformationResponse>

    fun bringAccompanyList(): Single<AccompanyListResponse>

}