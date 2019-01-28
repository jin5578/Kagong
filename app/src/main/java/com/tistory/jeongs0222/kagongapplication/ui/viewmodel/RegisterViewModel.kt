package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.viewpager.widget.PagerAdapter
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.ViewPagerProvider


class RegisterViewModel: DisposableViewModel() {

    private val _test1 = SingleLiveEvent<Any>()
    val test1: LiveData<Any> get() = _test1

    private val _test2 = SingleLiveEvent<Any>()
    val test2: LiveData<Any> get() = _test2

    private val TAG = "RegisterViewModel"

    lateinit var adapter: PagerAdapter

    fun bind(viewPagerProvider: ViewPagerProvider) {
        adapter = viewPagerProvider.getPagerAdapter()
    }

    fun setUpViewPager() {

    }

    fun test1Click() {
        _test1.call()

        Log.e(TAG, "test1Click")
    }

    fun test2Click() {
        _test2.call()

        Log.e(TAG, "test2Click")
    }

}