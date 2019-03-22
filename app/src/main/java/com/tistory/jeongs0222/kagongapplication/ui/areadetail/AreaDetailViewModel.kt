package com.tistory.jeongs0222.kagongapplication.ui.areadetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.accuweather.AccuWeatherResponse2
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

    private val _entireClick = SingleLiveEvent<Any>()
    val entireClick: LiveData<Any>
        get() = _entireClick

    private val _accuWeatherClick = SingleLiveEvent<Any>()
    val accuWeatherClick: LiveData<Any>
        get() = _accuWeatherClick

    private val _validateSchedue = MutableLiveData<String>()
    val validateSchedule: LiveData<String>
        get() = _validateSchedue

    private val _accuWeather = MutableLiveData<MutableList<AccuWeatherResponse2>>()
    val accuWeather: LiveData<MutableList<AccuWeatherResponse2>>
        get() = _accuWeather

    private val _areaInformation = MutableLiveData<MutableList<AreaInformationResult>>()
    val areaInformation: LiveData<MutableList<AreaInformationResult>>
        get() = _areaInformation

    private val _likeStatus = MutableLiveData<Int>()
    val likeStatus: LiveData<Int>
        get() = _likeStatus

    private val _accuWeatherVisibility = MutableLiveData<Boolean>()
    val accuWeatherVisibility: LiveData<Boolean>
        get() = _accuWeatherVisibility

    private val TAG = "AreaDetailViewModel"

    private lateinit var area: String

    init {
        _accuWeatherVisibility.value = false
    }

    fun bind(area: String) {
        this.area = area

        //areaForecast(areaDivider(area))
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun entireClickEvent() {
        _entireClick.call()

        _accuWeatherVisibility.value = false
    }

    fun accuWeatherClickEvent() {
        _accuWeatherClick.call()

        _accuWeatherVisibility.value = true
    }

    private fun areaForecast(locationKey: String) {
        areaDetailRepository.areaForecast(locationKey,"AQHvbOfPGdmG0fTCG2vk7Mf2a8WA9nsK")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _accuWeather.value = it.DailyForecasts
            }.doOnError {
                it.printStackTrace()
            }
            .subscribe()

    }

    private fun areaDivider(area: String): String {
        return when(area) {
            "런던" -> "328328"

            "파리" -> "623"

            "바르셀로나" -> "307297"

            else -> "308526"    //마드리드
        }
    }

    fun areaLikeClick(status: Int) {
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

    fun areaLikeValidate() {
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

    fun bringAreaInformation() {
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

    fun validateSchedule() {
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
