package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val apiModules: Module = module {

}

val kagongModules = module {
    /*factory {
        RegisterViewModelFactory()
    }*/
    viewModel {
        RegisterViewModel()
    }
}

val appModules = listOf(apiModules, kagongModules)