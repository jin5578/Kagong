package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaLog.FindAreaLogResult
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResponse
import io.reactivex.Single


class MainRepositoryImpl(private val hostApi: HostApi): MainRepository {
    override fun bringNickname(googlekey: String): Single<BringNicknameResult>
            = hostApi.bringNickname(googlekey)

    override fun bringHistory(googlekey: String): Single<FindAreaHistoryResponse>
            = hostApi.bringHistory(googlekey)

    override fun bringRecommendArea(): Single<RecommendAreaResponse>
            = hostApi.bringRecommendArea()

    override fun findArea(findcontent: String): Single<FindAreaResponse>
            = hostApi.findArea(findcontent)

    override fun findAreaLog(area: String, userid: String): Single<FindAreaLogResult>
            = hostApi.findAreaLog(area, userid)

    override fun bringSchedule(googlekey: String): Single<BringScheduleResponse>
            = hostApi.bringSchedule(googlekey)
}