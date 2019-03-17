package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AccompanyFragment: Fragment(), TabLayout.OnTabSelectedListener {

    private val TAG = "AccompanyFragment"

    private lateinit var binding: FragmentAccompanyBinding

    private val areaDetailTabViewModel by sharedViewModel<AreaDetailTabViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAccompanyBinding.inflate(inflater, container, false).apply {
            tViewModel = areaDetailTabViewModel
            lifecycleOwner = this@AccompanyFragment
        }

        binding.tabLayout.addOnTabSelectedListener(this@AccompanyFragment)

        Log.e(TAG, "onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "onViewCreated")
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        Log.e(TAG, p0!!.position.toString())

    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }
}