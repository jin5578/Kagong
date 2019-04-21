package com.tistory.jeongs0222.kagongapplication.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.PackageInfoProvider
import com.tistory.jeongs0222.kagongapplication.utils.RemoteControllerProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashViewModel : DisposableViewModel() {

    private val _openDelay = SingleLiveEvent<Boolean>()
    val openDelay: LiveData<Boolean>
        get() = _openDelay

    private lateinit var remoteControllerProvider: RemoteControllerProvider
    private lateinit var packageInfoProvider: PackageInfoProvider

    private lateinit var least_version: String
    private lateinit var app_version: String

    fun bind(remoteControllerProvider: RemoteControllerProvider, packageInfoProvider: PackageInfoProvider) {
        this.remoteControllerProvider = remoteControllerProvider
        this.packageInfoProvider = packageInfoProvider

        compareVersion()
    }

    private fun compareVersion() {
        least_version = remoteControllerProvider.fetchLeastVersion()

        app_version = packageInfoProvider.bringVersionName()

        Log.e("least_version", least_version)
        Log.e("app_version", app_version)

        if(app_version != least_version) {
            delayTime()
        } else {
            Log.e("123", "!23")
        }
    }

    private fun delayTime(): Disposable =
        Completable.timer(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _openDelay.value = true

                onCleared()
            }
}