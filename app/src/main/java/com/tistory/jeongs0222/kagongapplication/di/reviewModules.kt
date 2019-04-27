package com.tistory.jeongs0222.kagongapplication.di

import com.tistory.jeongs0222.kagongapplication.model.repository.ReviewRepository
import com.tistory.jeongs0222.kagongapplication.model.repository.ReviewRepositoryImpl
import com.tistory.jeongs0222.kagongapplication.ui.review.ReviewViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val reviewModules = module {

    viewModel {
        ReviewViewModel(get())
    }

    factory {
        ReviewRepositoryImpl(get()) as ReviewRepository
    }
}