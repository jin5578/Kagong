package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findarea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendResponse
import io.reactivex.Single


interface MainRepository {
    fun bringNickname(googlekey: String): Single<BringNicknameResult>

    fun bringHistory(googlekey: String): Single<AreaSearchResponse>

    fun bringRecommendArea(): Single<RecommendResponse>

    fun findArea(findcontent: String): Single<FindAreaResponse>
}