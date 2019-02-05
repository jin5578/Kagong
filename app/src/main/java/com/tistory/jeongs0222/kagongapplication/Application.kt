package com.tistory.jeongs0222.kagongapplication

import android.app.Application
import com.tistory.jeongs0222.kagongapplication.di.kagongModules
import org.koin.android.ext.android.startKoin


class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, kagongModules)
    }
}