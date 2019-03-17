package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.AddScheduleRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.AddScheduleRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.addschedule.AddScheduleViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val addScheduleModule = module {
    viewModel {
        AddScheduleViewModel(get())
    }

    factory {
        AddScheduleRepositoryImpl(get()) as AddScheduleRepository
    }
}