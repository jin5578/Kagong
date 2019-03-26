package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


interface SettingRepository {

    fun deleteUser(googlekey: String): Single<BasicResult>

}