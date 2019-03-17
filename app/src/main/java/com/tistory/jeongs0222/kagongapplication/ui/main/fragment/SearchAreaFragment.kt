package com.tistory.jeongs0222.kagongapplication.ui.view.main.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchAreaBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.main.adapter.AreaSearchHistoryAdapter
import com.tistory.jeongs0222.kagongapplication.ui.view.main.adapter.FindAreaAdapter
import com.tistory.jeongs0222.kagongapplication.ui.view.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchAreaFragment: Fragment(), TextWatcher {

    private lateinit var binding: FragmentSearchAreaBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchAreaBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@SearchAreaFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.areaSearch.addTextChangedListener(this@SearchAreaFragment)

        binding.recentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchAreaFragment.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AreaSearchHistoryAdapter(this@SearchAreaFragment, mainViewModel)
        }

        binding.findRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchAreaFragment.context)
            adapter = FindAreaAdapter(this@SearchAreaFragment, mainViewModel)
        }

        mainViewModel.selectedHistoryClick.observe(this@SearchAreaFragment, Observer {
            mainViewModel.findAreaLog()
        })

        mainViewModel.selectedSearchClick.observe(this@SearchAreaFragment, Observer {
            mainViewModel.findAreaLog()
        })
    }

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        mainViewModel.findArea(charSequence!!)
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}