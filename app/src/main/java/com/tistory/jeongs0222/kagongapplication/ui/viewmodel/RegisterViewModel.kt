package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewpager.widget.PagerAdapter
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.PersonalInfoFragment
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.SingleLiveEvent
import com.tistory.jeongs0222.kagongapplication.utils.ViewPagerProvider


class RegisterViewModel: DisposableViewModel() {

    private val _test1 = SingleLiveEvent<Any>()
    val test1: LiveData<Any> get() = _test1

    private val _test2 = SingleLiveEvent<Any>()
    val test2: LiveData<Any> get() = _test2

    private val TAG = "RegisterViewModel"

    private lateinit var fragmentProvider: FragmentProvider

    private var FRAGMENT_ID: Fragment = PersonalInfoFragment()


    init {
        Log.e(TAG, "RegisterViewModelCreated")
    }

    fun bind(fragmentProvider: FragmentProvider) {
        this@RegisterViewModel.fragmentProvider = fragmentProvider

        initView()
    }

    private fun initView() {
        fragmentProvider.initFragment()
    }

    fun replaceFragment() {
        fragmentProvider.replaceFragment(FRAGMENT_ID)
    }


    fun test1Click() {
        _test1.call()

        FRAGMENT_ID = BasicInfoFragment()

        Log.e(TAG, "test1Click")
    }

    fun test2Click() {
        _test2.call()

        FRAGMENT_ID = PersonalInfoFragment()

        Log.e(TAG, "test2Click")
    }

}