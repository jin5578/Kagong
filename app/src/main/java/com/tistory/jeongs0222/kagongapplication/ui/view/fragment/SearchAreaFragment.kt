package com.tistory.jeongs0222.kagongapplication.ui.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchAreaBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.AreaSearchHistoryAdapter
import com.tistory.jeongs0222.kagongapplication.ui.adapter.FindAreaAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchAreaFragment: Fragment(), TextWatcher {

    private lateinit var binding: FragmentSearchAreaBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchAreaBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            setLifecycleOwner(this@SearchAreaFragment)
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

        mainViewModel.selectedHistory.observe(this@SearchAreaFragment, Observer {
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