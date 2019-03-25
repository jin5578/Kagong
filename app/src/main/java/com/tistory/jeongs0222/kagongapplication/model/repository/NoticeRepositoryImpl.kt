package com.tistory.jeongs0222.kagongapplication.model.repository

import com.tistory.jeongs0222.kagongapplication.api.HostApi
import com.tistory.jeongs0222.kagongapplication.model.host.bringNotice.BringNoticeResponse
import io.reactivex.Single


class NoticeRepositoryImpl(private val hostApi: HostApi): NoticeRepository {
    override fun bringNotice(): Single<BringNoticeResponse>
            = hostApi.bringNotice()
}