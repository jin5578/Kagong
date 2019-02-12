package com.tistory.jeongs0222.kagongapplication.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentHomeBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.RecommendAreaAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            setLifecycleOwner(this@HomeFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recommendRecyclerView.apply {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = RecommendAreaAdapter(this@HomeFragment, mainViewModel)
        }

        mainViewModel.selectedRecommend.observe(this@HomeFragment, Observer {
            mainViewModel.findAreaLog(it)
        })
    }
}