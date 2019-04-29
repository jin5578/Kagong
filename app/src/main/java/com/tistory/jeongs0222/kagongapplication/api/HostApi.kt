package com.tistory.jeongs0222.kagongapplication.api

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaImage.BringAreaImageResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaList.BringAreaListResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringLocationDetail.BringLocationDetailResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNickname.BringNicknameResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNicknameAndIntro.BringNicknameAndIntroResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResponse
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaLog.FindAreaLogResult
import com.tistory.jeongs0222.kagongapplication.model.host.locationMap.LocationMapResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResponse
import com.tistory.jeongs0222.kagongapplication.model.host.validateSchedule.ValidateScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface HostApi {

    //Key Check - LoginActivity
    @FormUrlEncoded
    @POST("keycheck.php")
    fun keyCheck(@Field("googlekey") googlekey: String): Single<BasicResult> //0: 처음 연동, 가입하는 경우 1: 연동은 하였지만 가입을 하지 않은 경우 2: 이미 연동, 가입한 경우

    //Nickname Validate - RegisterActivity, ProfileActivity
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
        @Field("age") age: String,
        @Field("loginMethod") loginMethod: String
    ): Single<BasicResult> //0: 가입성공, 1: 가입실패

    //Bring Nickname And Introduce - MainActivity, ProfileActivity
    @FormUrlEncoded
    @POST("bringNicknameAndIntro.php")
    fun bringNicknameAndIntro(
        @Field("googlekey") googlekey: String
    ): Single<BringNicknameAndIntroResult>

    //Bring Area List - MainActivity ( MainAccompanyFragment)
    @GET("bringAreaList.php")
    fun bringAreaList(): Single<BringAreaListResponse>

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
        @Field("findcontent") findcontent: String,
        @Field("sort") sort: Int
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

    //Modify Profile - ProfileActivity
    @FormUrlEncoded
    @POST("updateProfile.php")
    fun updateProfile(
        @Field("googlekey") googlekey: String,
        @Field("nickname") nickname: String,
        @Field("introduce") introduce: String
    ): Single<BasicResult>

    //Upload Profile Image - ProfileActivity
    @Multipart
    @POST("uploadProfileImage.php")
    fun uploadProfileImage(
        @Part up_image: List<MultipartBody.Part>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Single<BasicResult>

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

    //Bring Area Image = AreaDetailActivity
    @FormUrlEncoded
    @POST("bringAreaImage.php")
    fun bringAreaImage(
        @Field("area") area: String
    ): Single<BringAreaImageResult>

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

    //Bring Accompany = AreaDetailTabActivity
    @FormUrlEncoded
    @POST("bringAccompany.php")
    fun bringAccompany(
        @Field("area") area: String,
        @Field("tab") tab: Int
    ): Single<BringAccompanyResponse>

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

    //Bring Nickname - AccompanyWriteActivity
    @FormUrlEncoded
    @POST("bringNicknameAndIntro.php")
    fun bringNickname(
        @Field("googlekey") googlekey: String
    ): Single<BringNicknameResult>

    //Accompany Write - AccompanyWriteActivity
    @FormUrlEncoded
    @POST("accompanyWrite.php")
    fun accompanyWrite(
        @Field("area") area: String,
        @Field("sort") sort: Int,
        @Field("googlekey") googlekey: String,
        @Field("content") content: String,
        @Field("writtenTime") writtenTime: String,
        @Field("meetingDate") meetingDate: String,
        @Field("link") link: String
    ): Single<BasicResult>  //0: 성공, 1: 실패

    //Bring Notice - NoticeActivity
    @GET("bringNotice.php")
    fun bringNotice(): Single<BringNoticeResponse>

    //withdrawal User - SettingActivity
    @FormUrlEncoded
    @POST("deleteUser.php")
    fun deleteUser(
        @Field("googlekey") googlekey: String
    ): Single<BasicResult>  //0: 성공, 1: 실패

    //written accompany
    @FormUrlEncoded
    @POST("writtenAccompany.php")
    fun writtenAccompany(
        @Field("userkey") userkey: String
    ): Single<WrittenAccompanyResponse>

    //delete accompany
    @FormUrlEncoded
    @POST("deleteAccompany.php")
    fun deleteAccompany(
        @Field("userkey") userkey: String,
        @Field("content") content: String,
        @Field("writtenTime") writtenTime: String
    ): Single<BasicResult>

    //bring location detail
    @FormUrlEncoded
    @POST("bringLocationDetail.php")
    fun bringLocationDetail(
        @Field("order") order: Int
    ): Single<BringLocationDetailResult>

    //location like click
    @FormUrlEncoded
    @POST("locationLikeClick.php")
    fun locationLikeClick(
        @Field("userkey") userkey: String,
        @Field("order") order: Int,
        @Field("status") status: Int
    ): Single<BasicResult>

    //location like validate
    @FormUrlEncoded
    @POST("locationLikeValidate.php")
    fun locationLikeValidate(
        @Field("userkey") userkey: String,
        @Field("order") order: Int
    ): Single<BasicResult>

    //bring review list
    @FormUrlEncoded
    @POST("bringLocationReview.php")
    fun bringReview(
        @Field("order") order: Int,
        @Field("sort") sort: Int
    ): Single<BringLocationReviewResponse>

    //location review write
    @FormUrlEncoded
    @POST("locationReviewWrite.php")
    fun locationReviewWrite(
        @Field("userkey") userkey: String,
        @Field("written_time") written_time: String,
        @Field("order") order: Int,
        @Field("content") content: String
    ): Single<BasicResult>

    //location map
    @FormUrlEncoded
    @POST("locationMap.php")
    fun locationMap(
        @Field("order") order: Int
    ): Single<LocationMapResult>
}