package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.main.adapter.BringScheduleAdapter
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ScheduleFragment: Fragment() {

    private lateinit var binding: FragmentScheduleBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentScheduleBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@ScheduleFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ScheduleFragment.context)
            adapter = BringScheduleAdapter(
                this@ScheduleFragment,
                mainViewModel
            )
        }
    }
}