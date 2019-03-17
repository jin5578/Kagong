package com.tistory.jeongs0222.kagongapplication.ui.areadetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.uid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AreaDetailViewModel(private val areaDetailRepository: AreaDetailRepository): DisposableViewModel() {


    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _validateSchedue = MutableLiveData<String>()
    val validateSchedule: LiveData<String>
        get() = _validateSchedue

    private val _areaInformation = MutableLiveData<MutableList<AreaInformationResult>>()
    val areaInformation: LiveData<MutableList<AreaInformationResult>>
        get() = _areaInformation

    private val _likeStatus = MutableLiveData<Int>()
    val likeStatus: LiveData<Int>
        get() = _likeStatus

    private val TAG = "AreaDetailViewModel"


    fun previousClickEvent() {
        _previousClick.call()
    }

    fun areaLikeClick(area: String, status: Int) {
        areaDetailRepository.areaLikeClick(uid, area, status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 2) {
                    _likeStatus.value = 0
                } else if(it.value == 0) {
                    _likeStatus.value = 1
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun areaLikeValidate(area: String) {
        areaDetailRepository.areaLikeValidate(uid, area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _likeStatus.value = it.value
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun bringAreaInformation(area: String) {
        areaDetailRepository.bringAreaDetail(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _areaInformation.value = it.bringinformation
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun validateSchedule(area: String) {
        areaDetailRepository.validateSchedule(uid, area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    _validateSchedue.value = "일정을 추가해보세요"
                } else {
                    _validateSchedue.value = it.date.removeRange(13, 18).replace("-", ".")
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}
