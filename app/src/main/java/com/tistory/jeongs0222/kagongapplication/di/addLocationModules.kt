package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.AddLocationRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.AddLocationRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.AddLocationViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val addLocationModules = module {
    factory {
        AddLocationRepositoryImpl(get()) as AddLocationRepository
    }
    viewModel {
        AddLocationViewModel(get())
    }
}