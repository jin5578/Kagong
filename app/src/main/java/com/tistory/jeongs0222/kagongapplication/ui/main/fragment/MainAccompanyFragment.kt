package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentMainAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import com.tistory.jeongs0222.kagongapplication.ui.main.adapter.AccompanyAdapter
import com.tistory.jeongs0222.kagongapplication.ui.main.adapter.BringAreaListAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainAccompanyFragment: Fragment(), TabLayout.OnTabSelectedListener {

    private lateinit var binding: FragmentMainAccompanyBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainAccompanyBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@MainAccompanyFragment
        }

        binding.tabLayout.addOnTabSelectedListener(this@MainAccompanyFragment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.areaRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainAccompanyFragment.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BringAreaListAdapter(
                this@MainAccompanyFragment,
                mainViewModel
            )
        }

        binding.accompanyRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainAccompanyFragment.context)
            adapter = AccompanyAdapter(this@MainAccompanyFragment, mainViewModel)
        }

        mainViewModel.bringAccompany(0)

        mainViewModel.areaChanged.observe(this@MainAccompanyFragment, Observer {
            binding.tabLayout.getTabAt(0)!!.select()
        })
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        mainViewModel.bringAccompany(tab!!.position)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

}