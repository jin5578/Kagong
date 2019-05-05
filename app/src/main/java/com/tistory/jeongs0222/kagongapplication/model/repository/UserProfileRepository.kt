package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.bringUserProfile.BringUserProfileResult
import io.reactivex.Single


interface UserProfileRepository {
    fun bringUserProfile(userkey: String): Single<BringUserProfileResult>
}