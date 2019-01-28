package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.PersonalInfoFragment


class RegisterAdapter(fm: FragmentManager, internal var numOfTabs: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BasicInfoFragment()

            1 -> PersonalInfoFragment()

            else -> null!!

        }
    }

    override fun getCount(): Int = numOfTabs
}