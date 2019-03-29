package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNicknameAndIntro.BringNicknameAndIntroResult
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody


interface ProfileRepository {
    fun bringNicknameAndIntro(googlekey: String): Single<BringNicknameAndIntroResult>

    fun nicknameValidate(nickname: String): Single<BasicResult>

    fun updateProfile(googlekey: String, nickname: String, introduce: String): Single<BasicResult>

    fun uploadProfileImage(up_image: List<MultipartBody.Part>, params: Map<String, @JvmSuppressWildcards RequestBody>): Single<BasicResult>
}