package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailTabRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.AreaDetailTabRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val areaDetailTabModules = module {
    viewModel {
        AreaDetailTabViewModel(get())
    }

    factory {
        AreaDetailTabRepositoryImpl(get()) as AreaDetailTabRepository
    }
}