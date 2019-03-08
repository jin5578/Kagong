package com.tistory.jeongs0222.kagongapplication.api

import com.tistory.jeongs0222.kagongapplication.model.host.addSchedule.AddScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.deleteSchedule.DeleteScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaLog.FindAreaLogResult
import com.tistory.jeongs0222.kagongapplication.model.host.keyCheck.KeyCheckResult
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.NicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.register.RegisterResult
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HostApi {

    //Nickname Validate
    @FormUrlEncoded
    @POST("nickname_validate.php")
    fun nicknameValidate(@Field("nickname") nickname: String): Single<NicknameResult>  //0: 사용가능, 1: 이미 존재, 2: 2 ~ 6자

    //Register
    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("userkey") userkey: String,
        @Field("usertoken") usertoken: String,
        @Field("nickname") nickname: String,
        @Field("sex") sex: String,
        @Field("age") age: String
    ): Single<RegisterResult> //0: 가입성공, 1: 가입실패

    //Key Check
    @FormUrlEncoded
    @POST("keycheck.php")
    fun keyCheck(@Field("googlekey") googlekey: String): Single<KeyCheckResult> //0: 처음 연동, 가입하는 경우 1: 연동은 하였지만 가입을 하지 않은 경우 2: 이미 연동, 가입한 경우

    //Bring Nickname
    @FormUrlEncoded
    @POST("bringNickname.php")
    fun bringNickname(@Field("googlekey") googlekey: String): Single<BringNicknameResult>

    //Bring search area history
    @FormUrlEncoded
    @POST("areasearchhistory.php")
    fun bringHistory(@Field("googlekey") googlekey: String): Single<FindAreaHistoryResponse>

    //Bring recommend area
    @GET("recommendarea.php")
    fun bringRecommendArea(): Single<RecommendAreaResponse>

    //Bring search area
    @FormUrlEncoded
    @POST("findarea.php")
    fun findArea(
        @Field("findcontent") findcontent: String
    ): Single<FindAreaResponse>

    //Find Area Log
    @FormUrlEncoded
    @POST("findarealog.php")
    fun findAreaLog(
        @Field("area") area: String,
        @Field("userid") userid: String
    ): Single<FindAreaLogResult>    //0: 성공, 1: 실패

    //Check Validate Schedule
    @FormUrlEncoded
    @POST("validateschedule.php")
    fun validateSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String
    ): Single<ValidateScheduleResult>       //0: 없음, 1: 있음

    //Bring Area Information
    @FormUrlEncoded
    @POST("bringareainformation.php")
    fun bringAreaInformation(
        @Field("area") area: String
    ): Single<AreaInformationResponse>

    //Add Schedule
    @FormUrlEncoded
    @POST("schedule_insert.php")
    fun addSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String,
        @Field("date") date: String
    ): Single<AddScheduleResult>    //0: 성공, 1: 실패

    //Bring Schedule
    @FormUrlEncoded
    @POST("bringschedule.php")
    fun bringSchedule(
        @Field("googlekey") googlekey: String
    ): Single<BringScheduleResponse>

    //Delete Schedule
    @FormUrlEncoded
    @POST("deleteschedule.php")
    fun deleteSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String
    ): Single<DeleteScheduleResult>   //0: 성공, 1: 실패

    //Bring Location
    @FormUrlEncoded
    @POST("findlocation.php")
    fun bringLocation(
        @Field("area") area: String,
        @Field("findlocation") findlocation: String
    ): Single<FindLocationResponse>


}