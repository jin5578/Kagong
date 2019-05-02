package com.tistory.jeongs0222.kagongapplication.ui.howToUse.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentScheduleHtuBinding
import com.tistory.jeongs0222.kagongapplication.ui.howToUse.HowToUseViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ScheduleHTUFragment: Fragment() {

    private val TAG = "ScheduleHTUFragment"

    private lateinit var binding: FragmentScheduleHtuBinding

    private val viewModel by sharedViewModel<HowToUseViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentScheduleHtuBinding.inflate(inflater, container, false).apply {
            hViewModel = viewModel
            lifecycleOwner = this@ScheduleHTUFragment
        }

        return binding.root
    }
}