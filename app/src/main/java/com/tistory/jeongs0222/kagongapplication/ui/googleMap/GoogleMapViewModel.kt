package com.tistory.jeongs0222.kagongapplication.ui.googleMap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tistory.jeongs0222.kagongapplication.model.host.locationMap.LocationMapResult
import com.tistory.jeongs0222.kagongapplication.model.repository.GoogleMapRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GoogleMapViewModel(private val googleMapRepository: GoogleMapRepository) : DisposableViewModel(),
    GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener {

    private val _locationItem = MutableLiveData<LocationMapResult>()
    val locationItem: LiveData<LocationMapResult>
        get() = _locationItem

    private val _informationVisibility = MutableLiveData<Boolean>()
    val informationVisibility: LiveData<Boolean>
        get() = _informationVisibility

    private lateinit var mapPosition: LatLng

    private val ZOOM_LEVEL = 16F


    init {
        _informationVisibility.value = true
    }


    fun locationMap(order: Int) {
        googleMapRepository.locationMap(order)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _locationItem.value = it
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    fun onMapReady(googleMap: GoogleMap?) {
        mapPosition = LatLng(_locationItem.value!!.latitude.toDouble(), _locationItem.value!!.longtitude.toDouble())

        googleMap!!.apply {
            setOnCameraMoveStartedListener(this@GoogleMapViewModel)
            setOnCameraIdleListener(this@GoogleMapViewModel)
        }

        with(googleMap) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(mapPosition, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(mapPosition))
        }
    }

    //사용자가 맵을 터치하여 이동하는 경우
    override fun onCameraMoveStarted(reason: Int) {
        if(reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            _informationVisibility.value = false
        }
    }

    //사용자가 맵 이동을 멈추는 경우
    override fun onCameraIdle() {
        _informationVisibility.value = true
    }
}