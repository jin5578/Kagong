package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.WrittenRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.WrittenRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.written.WrittenViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val writtenModules = module {
    viewModel {
        WrittenViewModel(get())
    }

    factory {
        WrittenRepositoryImpl(get()) as WrittenRepository
    }
}