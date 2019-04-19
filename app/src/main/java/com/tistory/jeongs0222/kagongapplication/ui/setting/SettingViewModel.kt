package com.tistory.jeongs0222.kagongapplication.ui.setting

import android.annotation.SuppressLint
import android.content.pm.PackageInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.repository.SettingRepository
import com.tistory.jeongs0222.kagongapplication.ui.DisposableViewModel
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.PackageInfoProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SettingViewModel(private val settingRepository: SettingRepository) : DisposableViewModel() {

    private val _previousClick = SingleLiveEvent<Any>()
    val previousClick: LiveData<Any>
        get() = _previousClick

    private val _termsOfUseClick = SingleLiveEvent<Any>()
    val termsOfUseClick: LiveData<Any>
        get() = _termsOfUseClick

    private val _withdrawalClick = SingleLiveEvent<Any>()
    val withdrawalClick: LiveData<Any>
        get() = _withdrawalClick

    private val _appShutDownRequest = SingleLiveEvent<Any>()
    val appShutDownRequest: LiveData<Any>
        get() = _appShutDownRequest

    private val _versionName = MutableLiveData<String>()
    val versionName: LiveData<String>
        get() = _versionName


    private lateinit var dbHelperProvider: DBHelperProvider
    private lateinit var messageProvider: MessageProvider
    private lateinit var packageInfoProvider: PackageInfoProvider

    private lateinit var userKey: String

    fun bind(dbHelperProvider: DBHelperProvider, messageProvider: MessageProvider, packageInfoProvider: PackageInfoProvider) {
        this.dbHelperProvider = dbHelperProvider
        this.messageProvider = messageProvider
        this.packageInfoProvider = packageInfoProvider

        userKey = dbHelperProvider.getDBHelper().getUserKey()
        _versionName.value = packageInfoProvider.bringVersionName()
    }

    fun previousClickEvent() {
        _previousClick.call()
    }

    fun termsOfUseClickEvent() {
        _termsOfUseClick.call()
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
                if (it.value == 0) {
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