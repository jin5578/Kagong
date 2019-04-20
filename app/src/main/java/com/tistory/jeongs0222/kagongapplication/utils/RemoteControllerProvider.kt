package com.tistory.jeongs0222.kagongapplication.utils

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tistory.jeongs0222.kagongapplication.BuildConfig
import com.tistory.jeongs0222.kagongapplication.R


interface RemoteControllerProvider {
    fun fetchLeastVersion(): String
}

class RemoteControllerProviderImpl: RemoteControllerProvider {
    private var remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
    private val LEAST_VERSION = "least_version"

    override fun fetchLeastVersion(): String {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setDeveloperModeEnabled(BuildConfig.DEBUG)
            .setMinimumFetchIntervalInSeconds(3600)
            .build()

        remoteConfig.setConfigSettings(configSettings)

        remoteConfig.setDefaults(R.xml.remote_config_defaults)

        return remoteConfig.getString(LEAST_VERSION)
    }
}