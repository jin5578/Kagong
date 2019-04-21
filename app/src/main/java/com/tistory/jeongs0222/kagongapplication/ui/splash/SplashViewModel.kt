package com.tistory.jeongs0222.kagongapplication.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
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
    private lateinit var messageProvider: MessageProvider

    private lateinit var least_version: String
    private lateinit var app_version: String

    fun bind(remoteControllerProvider: RemoteControllerProvider, packageInfoProvider: PackageInfoProvider, messageProvider: MessageProvider) {
        this.remoteControllerProvider = remoteControllerProvider
        this.packageInfoProvider = packageInfoProvider
        this.messageProvider = messageProvider

        compareVersion()
    }

    private fun compareVersion() {
        least_version = remoteControllerProvider.fetchLeastVersion()

        app_version = "\"" + packageInfoProvider.bringVersionName() + "\""

        if(app_version != least_version) {
            delayTime()
        } else {
            messageProvider.settingAlertDialog(2)
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