package com.tistory.jeongs0222.kagongapplication.ui.setting

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.SettingRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SettingViewModel(private val settingRepository: SettingRepository): DisposableViewModel() {

    private val _homePreviousClick = SingleLiveEvent<Any>()
    val homePreviousClick: LiveData<Any>
        get() = _homePreviousClick

    private val _withdrawalClick = SingleLiveEvent<Any>()
    val withdrawalClick: LiveData<Any>
        get() = _withdrawalClick

    private val _appShutDownRequest = SingleLiveEvent<Any>()
    val appShutDownRequest: LiveData<Any>
        get() = _appShutDownRequest


    private lateinit var dbHelperProvider: DBHelperProvider
    private lateinit var messageProvider: MessageProvider

    private lateinit var userKey: String


    fun bind(dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider) {
        this.dbHelperProvider = dbHelperProvider
        this.messageProvider = messageProvider

        userKey = dbHelperProvider.getDBHelper().getUserKey()
    }

    fun homePreviousClickEvent() {
        _homePreviousClick.call()
    }

    fun withdrawalClickEvent() {
        _withdrawalClick.call()
    }

    fun deleteSQLite() {
        dbHelperProvider.getDBHelper().deleteUserKey()

        deleteHost()
    }

    private fun deleteHost() {
        settingRepository.deleteUser(userKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                if(it.value == 0) {
                    messageProvider.toastMessage(it.message)

                    appShutDownRequest()
                } else {
                    messageProvider.toastMessage(it.message)
                }
            }
            .doOnError {
                it.printStackTrace()
            }
            .subscribe()
    }

    @SuppressLint("CheckResult")
    private fun appShutDownRequest() {
        Completable.timer(2000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _appShutDownRequest.call()
            }
    }
}