package com.tistory.jeongs0222.kagongapplication.ui.view.activity


import android.os.Bundle
import android.util.Log
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityMainBinding
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProvider
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProviderImpl

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main

    private val TAG = "MainActivity"

    private val dbHelperProvider = DBHelperProviderImpl(this) as DBHelperProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG + "googlekey", dbHelperProvider.getDBHelper().getGooglekey())

    }
}
