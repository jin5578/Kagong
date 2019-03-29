package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNicknameAndIntro.BringNicknameAndIntroResult
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody


class ProfileRepositoryImpl(private val hostApi: HostApi) : ProfileRepository {
    override fun bringNicknameAndIntro(googlekey: String): Single<BringNicknameAndIntroResult>
            = hostApi.bringNicknameAndIntro(googlekey)

    override fun nicknameValidate(nickname: String): Single<BasicResult>
            = hostApi.nicknameValidate(nickname)

    override fun updateProfile(googlekey: String, nickname: String, introduce: String): Single<BasicResult>
            = hostApi.updateProfile(googlekey, nickname, introduce)

    override fun uploadProfileImage(
        up_image: List<MultipartBody.Part>,
        params: Map<String, RequestBody>
    ): Single<BasicResult>
            = hostApi.uploadProfileImage(up_image, params)

}