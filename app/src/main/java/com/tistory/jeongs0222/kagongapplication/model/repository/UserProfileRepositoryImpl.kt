package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringTOPSchedule.BringTOPScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringUserProfile.BringUserProfileResult
import io.reactivex.Single


class UserProfileRepositoryImpl(private val hostApi: HostApi): UserProfileRepository {
    override fun bringUserProfile(userkey: String): Single<BringUserProfileResult>
        = hostApi.bringUserProfile(userkey)

    override fun userLikeClick(userKey: String, theOtherKey: String, status: Int): Single<BasicResult>
        = hostApi.userLikeClick(userKey, theOtherKey, status)

    override fun userLikeValidate(userKey: String, theOtherKey: String): Single<BasicResult>
        = hostApi.userLikeValidate(userKey, theOtherKey)

    override fun bringTOPSchedule(userKey: String): Single<BringTOPScheduleResponse>
         = hostApi.bringTOPSchedule(userKey)
}