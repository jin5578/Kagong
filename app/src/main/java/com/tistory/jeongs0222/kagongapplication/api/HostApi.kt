package com.tistory.jeongs0222.kagongapplication.api

import com.tistory.jeongs0222.kagongapplication.model.host.nickname.NicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.register.RegisterResult
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HostApi {

    //Nickname Validate
    @FormUrlEncoded
    @POST("nickname_validate.php")
    fun nicknameValidate(@Field("nickname") nickname: String): Single<NicknameResult>  //0: 사용가능, 1: 이미 존재, 2: 2 ~ 6자

    //Register
    @FormUrlEncoded
    @POST("register.php")
    fun register(@Field("userkey") userkey: String,
                 @Field("usertoken") usertoken: String,
                 @Field("nickname") nickname: String,
                 @Field("sex") sex: String,
                 @Field("age") age: String): Single<RegisterResult> //0: 가입성공, 1: 가입실패
}