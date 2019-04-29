package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.GoogleMapRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.GoogleMapRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.googleMap.GoogleMapViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val googleMapModules = module {
    viewModel {
        GoogleMapViewModel(get())
    }

    factory {
        GoogleMapRepositoryImpl(get()) as GoogleMapRepository
    }
}