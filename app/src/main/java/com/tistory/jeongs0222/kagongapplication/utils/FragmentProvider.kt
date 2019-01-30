package com.tistory.jeongs0222.kagongapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment


interface FragmentProvider {

    fun initFragment()

    fun replaceFragment(fragment: Fragment)
}

class FragmentProviderImpl(private val fragmentManager: FragmentManager): FragmentProvider {

    override fun initFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, BasicInfoFragment()).commit()

    }

    override fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit()

    }
}