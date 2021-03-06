package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.ui.inareement.InAgreementViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val inAgreementModules = module {
    viewModel {
        InAgreementViewModel()
    }
}