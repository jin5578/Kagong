package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentHomeBinding
import com.tistory.jeongs0222.kagongapplication.ui.main.adapter.RecommendAreaAdapter
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            mViewModel = viewModel
            lifecycleOwner = this@HomeFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recommendRecyclerView.apply {
            layoutManager = GridLayoutManager(this@HomeFragment.context, 2)
            adapter = RecommendAreaAdapter(
                this@HomeFragment,
                viewModel
            )
        }

        viewModel.selectedRecommendClick.observe(this@HomeFragment, Observer {
            viewModel.findAreaLog()
        })
    }
}