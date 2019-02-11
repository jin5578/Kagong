package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResult
import com.tistory.jeongs0222.kagongapplication.model.host.findarea.FindAreaResult
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendResult
import com.tistory.jeongs0222.kagongapplication.model.repository.MainRepository
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : DisposableViewModel(), MainEventListener {

    //MainActivity
    private val _viewFinish = MutableLiveData<Boolean>()
    val viewFinish: LiveData<Boolean>
        get() = _viewFinish

    //HomeFragment
    private val _searchAreaClick = SingleLiveEvent<Any>()
    val searchAreaClick: LiveData<Any>
        get() = _searchAreaClick

    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname

    private val _recommendArea = MutableLiveData<MutableList<RecommendResult>>()
    val recommendArea: LiveData<MutableList<RecommendResult>>
        get() = _recommendArea


    //SearchAreaFragment
    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _areaSearchHistory = MutableLiveData<MutableList<AreaSearchResult>>()
    val areaSearchHistory: LiveData<MutableList<AreaSearchResult>>
        get() = _areaSearchHistory

    private val _findArea = MutableLiveData<MutableList<FindAreaResult>>()
    val findArea: LiveData<MutableList<FindAreaResult>>
        get() = _findArea


    private val TAG = "MainViewModel"

    private var pressedTime: Long = 0

    private lateinit var messageProvider: MessageProvider


    init {
        _viewFinish.value = false

        bringRecommendArea()
        bringNickname(FirebaseAuth.getInstance().uid!!)
        bringHistory(FirebaseAuth.getInstance().uid!!)
    }

    fun bind(messageProvider: MessageProvider) {
        this.messageProvider = messageProvider
    }

    fun searchAreaClickEvent() {
        _searchAreaClick.call()

        Log.e(TAG, "searchAreaClick")
    }

    fun previousClickEvent() {
        _previousClick.call()

        Log.e(TAG, "previousClick")
    }

    fun bringNickname(googlekey: String) {
        mainRepository.bringNickname(googlekey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    _userNickname.value = it.nickname + "님 어디로 떠나시나요?"
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringHistory(googlekey: String) {
        mainRepository.bringHistory(googlekey)
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

    fun bringRecommendArea() {
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

    fun findArea(charSequence: CharSequence) {
        mainRepository.findArea(charSequence.toString())
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

    fun backPressed() {
        if(pressedTime == 0.toLong()) {
            messageProvider.toastMessage("한 번 더 누르면 종료됩니다")

            pressedTime = System.currentTimeMillis()
        } else {
            val secondsTime = (System.currentTimeMillis() - pressedTime).toInt()

            if(secondsTime > 2000) {
                messageProvider.toastMessage("한 번 더 누르면 종료됩니다")

                pressedTime = 0
            } else {
                _viewFinish.value = true
            }
        }
    }

    override fun clickEvent() {

    }
}

interface MainEventListener {

    fun clickEvent()

}