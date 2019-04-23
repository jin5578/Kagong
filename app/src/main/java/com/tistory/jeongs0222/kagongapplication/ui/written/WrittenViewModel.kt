package com.tistory.jeongs0222.kagongapplication.ui.written

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResult
import com.tistory.jeongs0222.kagongapplication.model.repository.WrittenRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class WrittenViewModel(private val writtenRepository: WrittenRepository): DisposableViewModel(), WrittenEventListener {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _writtenAccompanyList = MutableLiveData<MutableList<WrittenAccompanyResult>>()
    val writtenAccompanyList: LiveData<MutableList<WrittenAccompanyResult>>
        get() = _writtenAccompanyList

    private lateinit var userKey: String


    fun bind(dbHelperProvider: DBHelperProvider) {
        userKey = dbHelperProvider.getDBHelper().getUserKey()

        writtenAccompany()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    private fun writtenAccompany() {
        writtenRepository.writtenAccompany(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _writtenAccompanyList.value = it.writtenAccompany

                Log.e("writtenSize", _writtenAccompanyList.value!!.size.toString())
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }
}

interface WrittenEventListener {

}