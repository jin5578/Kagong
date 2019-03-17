package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.AddDetailScheduleRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.AddDetailScheduleRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.view.adddetailschedule.AddDetailScheduleViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val addDetailScheduleModules = module {
    viewModel {
        AddDetailScheduleViewModel(get())
    }

    factory {
        AddDetailScheduleRepositoryImpl(get()) as AddDetailScheduleRepository
    }
}