package com.tistory.jeongs0222.kagongapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tistory.jeongs0222.kagongapplication.R


interface FragmentProvider {
    fun initFragment(fragment: Fragment)

    fun replaceFragment(fragment: Fragment)

    fun addFragment(fragment: Fragment)

    fun addLocationFragment(sort: Int, fragment: Fragment)
}

class FragmentProviderImpl(private val fragmentManager: FragmentManager): FragmentProvider {

    override fun initFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit()
    }

    override fun addFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, fragment).commit()
    }

    override fun addLocationFragment(sort: Int, fragment: Fragment) {
        if(sort == 0) {
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.frameLayout, fragment).commit()
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.searchFrameLayout, fragment).commit()
        }
    }
}