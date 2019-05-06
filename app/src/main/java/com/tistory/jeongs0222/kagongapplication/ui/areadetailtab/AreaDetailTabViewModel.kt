package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailTabRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.ui.locationdetail.LocationDetailActivity
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailTabViewModel(private val areaDetailTabRepository: AreaDetailTabRepository): DisposableViewModel(),
    AreaDetailTabEventListener {

    //Entire
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _searchClick = SingleLiveEvent<Any>()
    val searchClick: LiveData<Any>
        get() = _searchClick


    //TourismFragment
    private val _areaLocation = MutableLiveData<MutableList<BringAreaLocationResult>>()
    val areaLocation: LiveData<MutableList<BringAreaLocationResult>>
        get() = _areaLocation


    //GoodPlaceFragment
    private val _areaGoodPlace = MutableLiveData<MutableList<BringAreaGoodPlaceResult>>()
    val areaGoodPlace: LiveData<MutableList<BringAreaGoodPlaceResult>>
        get() = _areaGoodPlace


    //AccompanyFragment
    private val _writeClick = SingleLiveEvent<Any>()
    val writeClick: LiveData<Any>
        get() = _writeClick

    private val _accompanyList = MutableLiveData<MutableList<BringAccompanyResult>>()
    val accompanyList: LiveData<MutableList<BringAccompanyResult>>
        get() = _accompanyList

    private val _userProfile = MutableLiveData<String>()
    val userProfile: LiveData<String>
        get() = _userProfile


    private lateinit var area: String
    private lateinit var intentProvider: IntentProvider

    private val TAG = "AreaDetailViewModel"


    fun bind(area: String, intentProvider: IntentProvider) {
        this.area = area
        this.intentProvider = intentProvider
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun searchClickEvent() {
        _searchClick.call()
    }

    fun writeClickEvent() {
        _writeClick.call()
    }

    fun bringAreaLocation(sort: Int, findlocation: String) {
        areaDetailTabRepository.bringAreaLocation(area, sort, findlocation)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaLocation.value = it.bringAreaLocation
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAreaGoodPlace(sort: Int, findGoodPlace: String) {
        areaDetailTabRepository.bringAreaGoodPlace(area, sort, findGoodPlace)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaGoodPlace.value = it.bringAreaGoodPlace
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAccompany(tab: Int) {
        areaDetailTabRepository.bringAccompany(area, tab)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _accompanyList.value = it.bringAccompany
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    override fun areaLocationClickEvent(order: Int) {
        //관광지 자세히 보기 페이지인데 현재는 추가하지 않는걸로....
        //intentProvider.iIntent(LocationDetailActivity::class.java, order)
    }

    override fun kakaoLinkClickEvent(link: String) {
        intentProvider.intentActionView(link)
    }

    override fun userProfileClickEvent(userid: String) {
        _userProfile.value = userid
    }

}

interface AreaDetailTabEventListener {

    fun areaLocationClickEvent(order: Int)

    fun kakaoLinkClickEvent(link: String)

    fun userProfileClickEvent(userid: String)

}