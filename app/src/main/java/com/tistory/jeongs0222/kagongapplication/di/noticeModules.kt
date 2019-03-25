package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.NoticeRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.NoticeRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.notice.NoticeViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val noticeModules = module {
    factory {
        NoticeRepositoryImpl(get()) as NoticeRepository
    }

    viewModel {
        NoticeViewModel(get())
    }
}