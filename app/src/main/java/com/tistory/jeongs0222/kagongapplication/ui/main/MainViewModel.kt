package com.tistory.jeongs0222.kagongapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaList.BringAreaListResult
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResult
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResult
import com.tistory.jeongs0222.kagongapplication.model.repository.MainRepository
import com.tistory.jeongs0222.kagongapplication.ui.adddetailschedule.AddDetailScheduleActivity
import com.tistory.jeongs0222.kagongapplication.ui.areadetail.AreaDetailActivity
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val mainRepository: MainRepository) : DisposableViewModel(),
    MainEventListener {

    //MainActivity
    private val _viewFinish = MutableLiveData<Boolean>()
    val viewFinish: LiveData<Boolean>
        get() = _viewFinish


    //HomeFragment, SearchAreaFragment, ProfileFragment
    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname

    private val _userImage = MutableLiveData<String>()
        val userImage: LiveData<String>
        get() = _userImage


    //HomeFragment
    private val _searchAreaClick = SingleLiveEvent<Any>()
    val searchAreaClick: LiveData<Any>
        get() = _searchAreaClick

    private val _selectedRecommendClick = SingleLiveEvent<Any>()
    val selectedRecommendClick: LiveData<Any>
        get() = _selectedRecommendClick

    private val _recommendArea = MutableLiveData<MutableList<RecommendAreaResult>>()
    val recommendArea: LiveData<MutableList<RecommendAreaResult>>
        get() = _recommendArea


    //MainAccompanyFragment
    private val _areaChangedClick = SingleLiveEvent<Any>()
    val areaChangedClick: LiveData<Any>
        get() = _areaChangedClick

    private val _accompanyWriteClick = SingleLiveEvent<Any>()
    val accompanyWriteClick: LiveData<Any>
        get() = _accompanyWriteClick

    private val _areaList = MutableLiveData<MutableList<BringAreaListResult>>()
    val areaList: LiveData<MutableList<BringAreaListResult>>
        get() = _areaList

    private val _areaRecyclerVisibility = MutableLiveData<Boolean>()
    val areaRecyclerVisibility: LiveData<Boolean>
        get() = _areaRecyclerVisibility

    private val _accompanyArea = MutableLiveData<String>()
    val accompanyArea: LiveData<String>
        get() = _accompanyArea

    private val _accompanyList = MutableLiveData<MutableList<BringAccompanyResult>>()
    val accompanyList: LiveData<MutableList<BringAccompanyResult>>
        get() = _accompanyList


    //ScheduleFragment
    private val _myScheduleList = MutableLiveData<MutableList<BringScheduleResult>>()
    val myScheduleList: LiveData<MutableList<BringScheduleResult>>
        get() = _myScheduleList


    //SearchAreaFragment
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _selectedHistoryClick = SingleLiveEvent<Any>()
    val selectedHistoryClick: LiveData<Any>
        get() = _selectedHistoryClick

    private val _selectedSearchClick = SingleLiveEvent<Any>()
    val selectedSearchClick: LiveData<Any>
        get() = _selectedSearchClick

    private val _areaSearchHistory = MutableLiveData<MutableList<FindAreaHistoryResult>>()
    val areaSearchHistory: LiveData<MutableList<FindAreaHistoryResult>>
        get() = _areaSearchHistory

    private val _findArea = MutableLiveData<MutableList<FindAreaResult>>()
    val findArea: LiveData<MutableList<FindAreaResult>>
        get() = _findArea


    //ProfileFragment
    private val _profileDetailClick = SingleLiveEvent<Any>()
    val profileDetailClick: LiveData<Any>
        get() = _profileDetailClick

    private val _settingClick = SingleLiveEvent<Any>()
    val settingClick: LiveData<Any>
        get() = _settingClick

    private val _noticeClick = SingleLiveEvent<Any>()
    val noticeClick: LiveData<Any>
        get() = _noticeClick

    private val _howToUseClick = SingleLiveEvent<Any>()
    val howToUseClick: LiveData<Any>
        get() = _howToUseClick

    private val _reviewClick = SingleLiveEvent<Any>()
    val reviewClick: LiveData<Any>
        get() = _reviewClick


    //Entire Fragment
    private val _selectedArea = MutableLiveData<String>()


    private val TAG = "MainViewModel"

    private var pressedTime: Long = 0

    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider

    private lateinit var userKey: String


    init {
        _viewFinish.value = false

        _accompanyArea.value = "런던"
    }

    fun bind(messageProvider: MessageProvider, intentProvider: IntentProvider, dbHelperProvider: DBHelperProvider) {
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider

        userKey = dbHelperProvider.getDBHelper().getUserKey()

        bringRecommendArea()
        bringNicknameAndIntro()
        bringAreaList()
    }

    fun changeAreaClickEvent() {
        _areaRecyclerVisibility.value = true
    }

    fun accompanyWriteClickEvent() {
        _accompanyWriteClick.call()
    }

    fun searchAreaClickEvent() {
        _searchAreaClick.call()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun settingClickEvent() {
        _settingClick.call()
    }

    fun noticeClickEvent() {
        _noticeClick.call()
    }

    fun howToUseClickEvent() {
        _howToUseClick.call()
    }

    fun reviewClickEvent() {
        _reviewClick.call()
    }

    fun profileDetailClickEvent() {
        _profileDetailClick.call()
    }

    fun bringNicknameAndIntro() {
        mainRepository.bringNicknameAndIntro(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _userNickname.value = it.nickname

                _userImage.value = it.image
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAccompany(tab: Int) {
        mainRepository.bringAccompany(_accompanyArea.value!!, tab)
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

    private fun bringAreaList() {
        mainRepository.bringAreaList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaList.value = it.areaList

                Log.e("_areaList_size", _areaList.value!!.size.toString())
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    private fun bringRecommendArea() {
        mainRepository.bringRecommendArea()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _recommendArea.value = it.recommendarea
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringHistory() {
        mainRepository.bringHistory(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaSearchHistory.value = it.areasearchhistory
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun findArea(findcontent: String, sort: Int) {
        mainRepository.findArea(findcontent, sort)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _findArea.value = it.findarea
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun findAreaLog() {
        mainRepository.findAreaLog(_selectedArea.value!!, userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    intentProvider.intentPutExtra(AreaDetailActivity::class.java, _selectedArea.value!!)
                } else {
                    messageProvider.toastMessage("잠시 후 다시 시도해주세요")
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringSchedule() {
        mainRepository.bringSchedule(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _myScheduleList.value = it.schedulelist
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun backPressed() {
        if (pressedTime == 0.toLong()) {
            messageProvider.toastMessage("한 번 더 누르면 종료됩니다")

            pressedTime = System.currentTimeMillis()
        } else {
            val secondsTime = (System.currentTimeMillis() - pressedTime).toInt()

            if (secondsTime > 2000) {
                messageProvider.toastMessage("한 번 더 누르면 종료됩니다")

                pressedTime = 0
            } else {
                _viewFinish.value = true
            }
        }
    }

    override fun recommendItemClickEvent(area: String) {
        _selectedArea.value = area

        _selectedRecommendClick.call()
    }

    override fun historyItemClickEvent(area: String) {
        _selectedArea.value = area

        _selectedHistoryClick.call()
    }

    override fun searchItemClickEvent(area: String) {
        _selectedArea.value = area

        _selectedSearchClick.call()
    }

    override fun myScheduleClickEvent(type: String, area: String) {
        if (type != "past") {
            intentProvider.intentPutExtra(AddDetailScheduleActivity::class.java, area)
        }
    }

    override fun areaListItemClickEvent(area: String) {
        _areaChangedClick.call()

        _accompanyArea.value = area

        _areaRecyclerVisibility.value = false

        bringAccompany(0)
    }

    override fun kakaoLinkClickEvent(link: String) {
        intentProvider.intentActionView(link)
    }

}

interface MainEventListener {
    fun recommendItemClickEvent(area: String)

    fun historyItemClickEvent(area: String)

    fun searchItemClickEvent(area: String)

    fun myScheduleClickEvent(type: String, area: String)

    fun areaListItemClickEvent(area: String)

    fun kakaoLinkClickEvent(link: String)
}