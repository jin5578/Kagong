package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.LocationDetailRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.LocationDetailRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.locationdetail.LocationDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val locationDetailModules = module {
    viewModel {
        LocationDetailViewModel(get())
    }

    factory {
        LocationDetailRepositoryImpl(get()) as LocationDetailRepository
    }
}