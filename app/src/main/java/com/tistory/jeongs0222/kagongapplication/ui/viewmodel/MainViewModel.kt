package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) : DisposableViewModel() {

    private val _userNickname = MutableLiveData<String>()
    val userNickname: LiveData<String>
        get() = _userNickname

    fun bringNickname(googlekey: String) {
        mainRepository.bringNickname(googlekey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if (it.value == 0) {
                    _userNickname.value = it.nickname + "님"
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}