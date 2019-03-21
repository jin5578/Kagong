package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.AccompanyWriteRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.AccompanyWriteRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.AccompanyWriteViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val accompanyWriteModules = module {
    viewModel {
        AccompanyWriteViewModel(get())
    }

    factory {
        AccompanyWriteRepositoryImpl(get()) as AccompanyWriteRepository
    }
}