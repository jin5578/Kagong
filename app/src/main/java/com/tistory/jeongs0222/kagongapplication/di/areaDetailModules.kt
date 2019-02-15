package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val areaDetailModules = module {
    viewModel {
        AreaDetailViewModel()
    }
}