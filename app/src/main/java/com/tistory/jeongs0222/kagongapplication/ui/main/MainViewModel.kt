package com.tistory.jeongs0222.kagongapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResult
import com.tistory.jeongs0222.kagongapplication.model.host.findAreaHistory.FindAreaHistoryResult
import com.tistory.jeongs0222.kagongapplication.model.host.findArea.FindAreaResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResult
import com.tistory.jeongs0222.kagongapplication.model.repository.MainRepository
import com.tistory.jeongs0222.kagongapplication.ui.adddetailschedule.AddDetailScheduleActivity
import com.tistory.jeongs0222.kagongapplication.ui.areadetail.AreaDetailActivity
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val mainRepository: MainRepository) : DisposableViewModel(),
    MainEventListener {

    //MainActivity
    private val _viewFinish = MutableLiveData<Boolean>()
    val viewFinish: LiveData<Boolean>
        get() = _viewFinish


    //HomeFragment
    private val _searchAreaClick = SingleLiveEvent<Any>()
    val searchAreaClick: LiveData<Any>
        get() = _searchAreaClick

    private val _selectedRecommendClick = SingleLiveEvent<Any>()
    val selectedRecommendClick: LiveData<Any>
        get() = _selectedRecommendClick

    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String>
        get() = _nickname

    private val _recommendArea = MutableLiveData<MutableList<RecommendAreaResult>>()
    val recommendArea: LiveData<MutableList<RecommendAreaResult>>
        get() = _recommendArea


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


    //ProfileDetailFragment
    private val _profilePreviousClick = SingleLiveEvent<Any>()
    val profilePreviousClick: LiveData<Any>
        get() = _profilePreviousClick

    private val _modifyClick = SingleLiveEvent<Any>()
    val modifyClick: LiveData<Any>
        get() = _modifyClick

    private val _introduce = MutableLiveData<String>()
    val introduce: LiveData<String>
        get() = _introduce


    //ProfileModifyFragment
    private val _modifyPreviousClick = SingleLiveEvent<Any>()
    val modifyPreviousClick: LiveData<Any>
        get() = _modifyPreviousClick

    private val _validateClick = SingleLiveEvent<Any>()
    val validateClick: LiveData<Any>
        get() = _validateClick

    private val _validateCheck = MutableLiveData<Boolean>()
    val validateCheck: LiveData<Boolean>
        get() = _validateCheck


    //Entire Fragment
    private val _selectedArea = MutableLiveData<String>()


    private val TAG = "MainViewModel"

    private var pressedTime: Long = 0

    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider


    init {
        _viewFinish.value = false

        bringRecommendArea()
        bringNickname()
    }

    fun bind(messageProvider: MessageProvider, intentProvider: IntentProvider) {
        this.messageProvider = messageProvider
        this.intentProvider = intentProvider
    }

    fun searchAreaClickEvent() {
        _searchAreaClick.call()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun profilePreviousClickEvent() {
        _profilePreviousClick.call()
    }

    fun modifyPreviousClickEvent() {
        _modifyPreviousClick.call()
    }

    fun profileDetailClickEvent() {
        _profileDetailClick.call()
    }

    fun modifyClickEvent() {
        _modifyClick.call()
    }

    fun validateClickEvent() {
        _validateClick.call()
    }

    private fun bringNickname() {
        mainRepository.bringNickname(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    _userNickname.value = it.nickname + "님 어디로 떠나시나요?"

                    _nickname.value = it.nickname
                }
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
        mainRepository.bringHistory(uid)
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
        mainRepository.findAreaLog(_selectedArea.value!!, uid)
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
        mainRepository.bringSchedule(uid)
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

    fun nicknameValidate(nickname: String) {
        mainRepository.nicknameValidate(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                when(it.value) {
                    0 -> {
                        messageProvider.toastMessage(it.message)
                        _validateCheck.value = true
                    }

                    1 -> {
                        messageProvider.toastMessage(it.message)
                        _validateCheck.value = false
                    }

                    2 -> {
                        messageProvider.toastMessage(it.message)
                        _validateCheck.value = false
                    }
                }
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

}

interface MainEventListener {
    fun recommendItemClickEvent(area: String)

    fun historyItemClickEvent(area: String)

    fun searchItemClickEvent(area: String)

    fun myScheduleClickEvent(type: String, area: String)
}