package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.LoginRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.LoginRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val loginModules = module {
    factory {
        LoginRepositoryImpl(get()) as LoginRepository
    }

    viewModel {
        LoginViewModel(get())
    }
}