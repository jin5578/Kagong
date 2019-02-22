package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.accompanylist.AccompanyListResponse
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import io.reactivex.Single


class AreaDetailRepositoryImpl(private val hostApi: HostApi) : AreaDetailRepository {
    override fun bringAreaDetail(area: String): Single<AreaInformationResponse>
            = hostApi.bringAreaInformation(area)

    override fun bringAccompanyList(): Single<AccompanyListResponse>
        = hostApi.bringAccompanyList()
}