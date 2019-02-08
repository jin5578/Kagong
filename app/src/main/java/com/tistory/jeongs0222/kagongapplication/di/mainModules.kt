package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.MainRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.MainRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val mainModules = module {
    factory {
        MainRepositoryImpl(get()) as MainRepository
    }

    viewModel {
        MainViewModel(get())
    }
}