package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AddLocationRepository
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddLocationViewModel(private val addLocationRepository: AddLocationRepository): DisposableViewModel(), AddLocationEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _confirmClick = SingleLiveEvent<Any>()
    val confirmClick: LiveData<Any>
        get() = _confirmClick

    private val _deleteClick = SingleLiveEvent<Any>()
    val deleteClick: LiveData<Any>
        get() = _deleteClick

    private val _additionClick = SingleLiveEvent<Any>()
    val additionClick: LiveData<Any>
        get() = _additionClick

    private val _searchClick = SingleLiveEvent<Any>()
    val searchClick: LiveData<Any>
        get() = _searchClick

    private val _tempClick = SingleLiveEvent<Any>()
    val tempClick: LiveData<Any>
        get() = _tempClick


    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    private val _confirmVisible = MutableLiveData<Boolean>()
    val confirmVisible: LiveData<Boolean>
        get() = _confirmVisible

    private val _searchFrameVisible = MutableLiveData<Boolean>()
    val searchFrameVisible: LiveData<Boolean>
        get() = _searchFrameVisible

    private val _findLocation = MutableLiveData<MutableList<FindLocationResult>>()
    val findLocation: LiveData<MutableList<FindLocationResult>>
        get() = _findLocation

    private val TAG = "AddLocationViewModel"

    private var fragmentPosition = 1

    private lateinit var area: String


    init {
        _title.value = "이동 경로 표시"
        _confirmVisible.value = true
        _searchFrameVisible.value = false
    }

    fun bind(area: String) {
        this.area = area
    }

    fun findLocation(charSequence: CharSequence) {
        addLocationRepository.findLocation(area, charSequence.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _findLocation.value = it.findlocation
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun previousClickEvent() {
        if(fragmentPosition == 1) {
            _previousClick.call()
        } else {
            _title.value = "이동 경로 표시"
            _confirmVisible.value = true
            _searchFrameVisible.value = false

            fragmentPosition = 1
        }

        Log.e(TAG, "previousClick")
    }

    fun confirmClickEvent() {
        _confirmClick.call()

        Log.e(TAG, "confirmClick")
    }

    fun deleteClickEvent() {
        _deleteClick.call()

        Log.e(TAG, "deleteClick")
    }

    fun additionClickEvent() {
        _additionClick.call()

        Log.e(TAG, "additionClick")
    }

    fun searchLocationClickEvent() {
        _searchClick.call()

        _title.value = "위치 검색하기"
        _confirmVisible.value = false
        _searchFrameVisible.value = true

        fragmentPosition = 2
    }

    /*fun tempClickEvent() {
        _tempClick.call()

        _title.value = "이동 경로 표시"
        _confirmVisible.value = true
        _searchFrameVisible.value = false
    }*/

    override fun locationItemClickEvent() {

    }
}

interface AddLocationEventListener {

    fun locationItemClickEvent()

}