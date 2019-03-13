package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.mainfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.BringScheduleAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.MainViewModel
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

        Log.e("scheduleFragment", "onresume")

        mainViewModel.myScheduleList.observe(this@ScheduleFragment, Observer {
            if(it.size == 0) {
                binding.itemNull.visibility = View.VISIBLE
            } else {
                binding.itemNull.visibility = View.GONE
            }
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ScheduleFragment.context)
            adapter = BringScheduleAdapter(this@ScheduleFragment, mainViewModel)
        }
    }
}