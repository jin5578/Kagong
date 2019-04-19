package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context


interface PackageInfoProvider {

    fun bringVersionName(): String

}

class PackageInfoProviderImpl(private val context: Context): PackageInfoProvider {
    override fun bringVersionName(): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }
}