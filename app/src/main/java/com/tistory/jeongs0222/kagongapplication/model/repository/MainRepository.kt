package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaLog.FindAreaLogResult
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResponse
import io.reactivex.Single


interface MainRepository {
    fun bringNickname(googlekey: String): Single<BringNicknameResult>

    fun bringHistory(googlekey: String): Single<FindAreaHistoryResponse>

    fun bringRecommendArea(): Single<RecommendAreaResponse>

    fun findArea(findcontent: String): Single<FindAreaResponse>

    fun findAreaLog(area: String, userid: String): Single<FindAreaLogResult>

    fun bringSchedule(googlekey: String): Single<BringScheduleResponse>
}