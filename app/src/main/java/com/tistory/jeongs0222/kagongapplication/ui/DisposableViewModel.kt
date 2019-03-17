package com.tistory.jeongs0222.kagongapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class DisposableViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val TAG = "DisposableViewModel"

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()

        Log.e(TAG, "compositeDisposable Clear")

        super.onCleared()
    }
}