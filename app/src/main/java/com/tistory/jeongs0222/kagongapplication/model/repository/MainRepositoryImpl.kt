package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaList.BringAreaListResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringNicknameAndIntro.BringNicknameAndIntroResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaLog.FindAreaLogResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResponse
import io.reactivex.Single


class MainRepositoryImpl(private val hostApi: HostApi): MainRepository {
    override fun bringNicknameAndIntro(googlekey: String): Single<BringNicknameAndIntroResult>
            = hostApi.bringNicknameAndIntro(googlekey)

    override fun bringHistory(googlekey: String): Single<FindAreaHistoryResponse>
            = hostApi.bringHistory(googlekey)

    override fun bringRecommendArea(): Single<RecommendAreaResponse>
            = hostApi.bringRecommendArea()

    override fun findArea(findcontent: String, sort: Int): Single<FindAreaResponse>
            = hostApi.findArea(findcontent, sort)

    override fun findAreaLog(area: String, userid: String): Single<FindAreaLogResult>
            = hostApi.findAreaLog(area, userid)

    override fun bringSchedule(googlekey: String): Single<BringScheduleResponse>
            = hostApi.bringSchedule(googlekey)

    override fun bringAreaList(): Single<BringAreaListResponse>
            = hostApi.bringAreaList()

    override fun bringAccompany(area: String, tab: Int): Single<BringAccompanyResponse>
            = hostApi.bringAccompany(area, tab)
}