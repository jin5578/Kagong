package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.BasicResult
import io.reactivex.Single


interface LoginRepository {
    fun keyCheck(googlekey: String): Single<BasicResult>
}