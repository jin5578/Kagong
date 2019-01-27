package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SplashViewModel : DisposableViewModel() {

    private val _openDelay = SingleLiveEvent<Boolean>()
    val openDelay: LiveData<Boolean> get() = _openDelay

    init {
        addDisposable(delayTime())
    }

    private fun delayTime(): Disposable =
        Completable.timer(3000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _openDelay.value = true

                onCleared()
            }

}