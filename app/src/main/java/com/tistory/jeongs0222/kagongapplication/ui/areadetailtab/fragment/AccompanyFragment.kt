package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabViewModel
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter.BringAccompanyAdapter
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AccompanyFragment.context)
            adapter = BringAccompanyAdapter(this@AccompanyFragment, areaDetailTabViewModel)
        }

        areaDetailTabViewModel.bringAccompany(0)
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        areaDetailTabViewModel.bringAccompany(p0!!.position)
        Log.e(TAG, p0!!.position.toString())
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }
}