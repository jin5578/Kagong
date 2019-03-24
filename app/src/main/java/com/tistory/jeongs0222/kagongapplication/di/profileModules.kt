package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.ProfileRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.ProfileRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val profileModules = module {
    viewModel {
        ProfileViewModel(get())
    }

    factory {
        ProfileRepositoryImpl(get()) as ProfileRepository
    }
}