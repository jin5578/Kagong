package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.SettingRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.SettingRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val settingModules = module {
    viewModel {
        SettingViewModel(get())
    }

    factory {
        SettingRepositoryImpl(get()) as SettingRepository
    }
}