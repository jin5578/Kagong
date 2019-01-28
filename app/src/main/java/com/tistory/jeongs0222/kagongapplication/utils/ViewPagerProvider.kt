package com.tistory.jeongs0222.kagongapplication.utils

import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import com.tistory.jeongs0222.kagongapplication.ui.adapter.RegisterAdapter


interface ViewPagerProvider {
    fun getPagerAdapter(): PagerAdapter

}

class ViewPagerProviderImpl(private val fragmentManager: FragmentManager): ViewPagerProvider {

    override fun getPagerAdapter(): PagerAdapter = RegisterAdapter(fragmentManager, 2)

}