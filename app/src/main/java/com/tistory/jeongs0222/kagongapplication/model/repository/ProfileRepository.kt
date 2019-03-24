package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNicknameAndIntro.BringNicknameAndIntroResult
import io.reactivex.Single


interface ProfileRepository {
    fun bringNicknameAndIntro(googlekey: String): Single<BringNicknameAndIntroResult>

    fun nicknameValidate(nickname: String): Single<BasicResult>

    fun updateProfile(googlekey: String, nickname: String, introduce: String): Single<BasicResult>
}