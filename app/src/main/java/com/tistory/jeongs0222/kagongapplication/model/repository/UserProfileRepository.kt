package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringTOPSchedule.BringTOPScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringUserProfile.BringUserProfileResult
import io.reactivex.Single


interface UserProfileRepository {
    fun bringUserProfile(userkey: String): Single<BringUserProfileResult>

    fun userLikeClick(
        userKey: String,
        theOtherKey: String,
        status: Int
    ): Single<BasicResult>

    fun userLikeValidate(
        userKey: String,
        theOtherKey: String
    ): Single<BasicResult>

    fun bringTOPSchedule(
        userKey: String
    ): Single<BringTOPScheduleResponse>
}