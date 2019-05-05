package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringUserProfile.BringUserProfileResult
import io.reactivex.Single


class UserProfileRepositoryImpl(private val hostApi: HostApi): UserProfileRepository {
    override fun bringUserProfile(userkey: String): Single<BringUserProfileResult>
        = hostApi.bringUserProfile(userkey)



}