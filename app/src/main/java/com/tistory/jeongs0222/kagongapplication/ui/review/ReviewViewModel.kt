package com.tistory.jeongs0222.kagongapplication.ui.review

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResult
import com.tistory.jeongs0222.kagongapplication.model.repository.ReviewRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat


class ReviewViewModel(private val reviewRepository: ReviewRepository): DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _checkClick = SingleLiveEvent<Any>()
    val checkClick: LiveData<Any>
        get() = _checkClick

    private val _writeClick = SingleLiveEvent<Any>()
    val writeClick: LiveData<Any>
        get() = _writeClick

    private val _viewFinishRequest = MutableLiveData<Boolean>()
    val viewFinishRequest: LiveData<Boolean>
        get() = _viewFinishRequest

    private val _writeClickable = MutableLiveData<Boolean>()
    val writeClickable: LiveData<Boolean>
        get() = _writeClickable

    private val _locationReviewItem = MutableLiveData<MutableList<BringLocationReviewResult>>()
    val locationReviewItem: LiveData<MutableList<BringLocationReviewResult>>
        get() = _locationReviewItem

    private val TAG = "ReviewViewModel"

    private lateinit var userkey: String
    private lateinit var messageProvider: MessageProvider

    private var order: Int = 0

    init {
        _writeClickable.value = true
    }

    fun bind(order: Int, dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider) {
        this.order = order
        userkey = dbHelperProvider.getDBHelper().getUserKey()
        this.messageProvider = messageProvider
    }

    fun previousClickEvent() {
        Log.e(TAG, "previousClick")

        _previousClick.call()
    }

    fun checkClickEvent() {
        _checkClick.call()
    }

    fun writeClickEvent() {
        _writeClick.call()
    }

    fun locationReviewWrite(content: String) {
        _writeClickable.value = false

        reviewRepository.locationReviewWrite(userkey, writtenTime(), order, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0)
                    _viewFinishRequest.value = true
                else {
                    messageProvider.toastMessage(it.message)

                    _writeClickable.value = true
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringReview() {
        reviewRepository.bringReview(order, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _locationReviewItem.value = it.bringLocationReview
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    @SuppressLint("SimpleDateFormat")
    private fun writtenTime(): String {
        val format1 = SimpleDateFormat("yyyy.MM.dd")

        return format1.format(System.currentTimeMillis())
    }
}