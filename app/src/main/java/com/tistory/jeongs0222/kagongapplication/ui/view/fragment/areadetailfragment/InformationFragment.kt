package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentInformationBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.AreaInformationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class InformationFragment: Fragment() {

    private lateinit var binding: FragmentInformationBinding

    private val areaDetailViewModel by sharedViewModel<AreaDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInformationBinding.inflate(inflater, container, false).apply {
            dViewModel = areaDetailViewModel
            lifecycleOwner = this@InformationFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@InformationFragment.context)
            adapter = AreaInformationAdapter(this@InformationFragment)
        }
    }
}