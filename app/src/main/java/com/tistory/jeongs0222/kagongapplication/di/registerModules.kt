package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.RegisterRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.RegisterRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.view.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val registerModules = module {
    viewModel {
        RegisterViewModel(get())
    }

    factory {
        RegisterRepositoryImpl(get()) as RegisterRepository
    }
}