package com.tistory.jeongs0222.kagongapplication.api

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaLog.FindAreaLogResult
import com.tistory.jeongs0222.kagongapplication.model.host.nickname.BringNicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HostApi {

    //Key Check - LoginActivity
    @FormUrlEncoded
    @POST("keycheck.php")
    fun keyCheck(@Field("googlekey") googlekey: String): Single<BasicResult> //0: 처음 연동, 가입하는 경우 1: 연동은 하였지만 가입을 하지 않은 경우 2: 이미 연동, 가입한 경우

    //Nickname Validate - RegisterActivity
    @FormUrlEncoded
    @POST("nickname_validate.php")
    fun nicknameValidate(@Field("nickname") nickname: String): Single<BasicResult>  //0: 사용가능, 1: 이미 존재, 2: 2 ~ 6자

    //Register - RegisterActivity
    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("userkey") userkey: String,
        @Field("usertoken") usertoken: String,
        @Field("nickname") nickname: String,
        @Field("sex") sex: String,
        @Field("age") age: String
    ): Single<BasicResult> //0: 가입성공, 1: 가입실패

    //Bring Nickname - MainActivity
    @FormUrlEncoded
    @POST("bringNickname.php")
    fun bringNickname(@Field("googlekey") googlekey: String): Single<BringNicknameResult>

    //Bring search area history - MainActivity
    @FormUrlEncoded
    @POST("areasearchhistory.php")
    fun bringHistory(@Field("googlekey") googlekey: String): Single<FindAreaHistoryResponse>

    //Bring recommend area - MainActivity
    @GET("recommendarea.php")
    fun bringRecommendArea(): Single<RecommendAreaResponse>

    //Bring search area - MainActivity
    @FormUrlEncoded
    @POST("findarea.php")
    fun findArea(
        @Field("findcontent") findcontent: String
    ): Single<FindAreaResponse>

    //Find Area Log - MainActivity
    @FormUrlEncoded
    @POST("findarealog.php")
    fun findAreaLog(
        @Field("area") area: String,
        @Field("userid") userid: String
    ): Single<FindAreaLogResult>    //0: 성공, 1: 실패

    //Bring Schedule - MainActivity
    @FormUrlEncoded
    @POST("bringschedule.php")
    fun bringSchedule(
        @Field("googlekey") googlekey: String
    ): Single<BringScheduleResponse>

    //Bring Detail Schedule - MainActivity
    @FormUrlEncoded
    @POST("bringdetailschedule.php")
    fun bringDetailSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String
    ): Single<BringDetailScheduleResponse>

    //Area Like Click - AreaDetailActivity
    @FormUrlEncoded
    @POST("arealikeclick.php")
    fun areaLikeClick(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String,
        @Field("status") status: Int
    ): Single<BasicResult>

    //Area Like Validate - AreaDetailActivity
    @FormUrlEncoded
    @POST("arealikevalidate.php")
    fun areaLikeValidate(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String
    ): Single<BasicResult>  //0: 좋아요 X 1: 좋아요 O

    //Check Validate Schedule - AreaDetailActivity
    @FormUrlEncoded
    @POST("validateschedule.php")
    fun validateSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String
    ): Single<ValidateScheduleResult>       //0: 없음, 1: 있음

    //Bring Area Information - AreaDetailActivity
    @FormUrlEncoded
    @POST("bringareainformation.php")
    fun bringAreaInformation(
        @Field("area") area: String
    ): Single<AreaInformationResponse>

    //Add Schedule - AddScheduleActivity
    @FormUrlEncoded
    @POST("schedule_insert.php")
    fun addSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String,
        @Field("date") date: String
    ): Single<BasicResult>    //0: 성공, 1: 실패

    //Area Location - AreaDetailTabActivity, AddLocationActivity
    @FormUrlEncoded
    @POST("bringAreaLocation.php")
    fun bringAreaLocation(
        @Field("area") area: String,
        @Field("sort") sort: Int,
        @Field("findlocation") findlocation: String
    ): Single<BringAreaLocationResponse>

    //Area Good Place - AreaDetailTabActivity
    @FormUrlEncoded
    @POST("bringAreaGoodPlace.php")
    fun bringAreaGoodPlace(
        @Field("area") area: String,
        @Field("sort") sort: Int,
        @Field("findGoodPlace") findGoodPlace: String
    ): Single<BringAreaGoodPlaceResponse>

    //Delete Schedule - AddDetailScheduleActivity
    @FormUrlEncoded
    @POST("deleteschedule.php")
    fun deleteSchedule(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String
    ): Single<BasicResult>   //0: 성공, 1: 실패

    //Register Location - AddLocationActivity
    @FormUrlEncoded
    @POST("registerlocation.php")
    fun registerLocation(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String,
        @Field("location") location: String,
        @Field("sort") sort: String
    ): Single<BasicResult>   //0: 성공, 1: 실패

    //Delete Location - AddLocationActivity
    @FormUrlEncoded
    @POST("deletelocation.php")
    fun deleteLocation(
        @Field("googlekey") googlekey: String,
        @Field("area") area: String,
        @Field("sort") sort: String
    ): Single<BasicResult>     //0: 성공, 1: 실패
}