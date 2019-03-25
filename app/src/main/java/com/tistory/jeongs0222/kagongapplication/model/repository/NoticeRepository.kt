package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResponse
import io.reactivex.Single


interface NoticeRepository {
    fun bringNotice(): Single<BringNoticeResponse>
}