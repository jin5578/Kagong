package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.areadetail.AreaDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val areaDetailModules = module {
    viewModel {
        AreaDetailViewModel(get())
    }

    factory {
        AreaDetailRepositoryImpl(get(), get()) as AreaDetailRepository
    }
}