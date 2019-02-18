package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddScheduleViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val addScheduleModule = module {
    viewModel {
        AddScheduleViewModel()
    }
}