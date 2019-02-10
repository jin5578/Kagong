package com.tistory.jeongs0222.kagongapplication.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchAreaBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.AreaSearchHistoryAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchAreaFragment: Fragment() {

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

        binding.recentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchAreaFragment.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AreaSearchHistoryAdapter(this@SearchAreaFragment, mainViewModel)
        }
    }
}