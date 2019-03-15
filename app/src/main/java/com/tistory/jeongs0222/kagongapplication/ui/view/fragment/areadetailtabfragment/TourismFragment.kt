package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailtabfragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentTourismBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.BringAreaLocationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailTabViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TourismFragment: Fragment() {

    private lateinit var binding: FragmentTourismBinding

    private val areaDetailTabViewModel by sharedViewModel<AreaDetailTabViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTourismBinding.inflate(inflater, container, false).apply {
            tViewModel = areaDetailTabViewModel
            lifecycleOwner = this@TourismFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        areaDetailTabViewModel.bringAreaLocation()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TourismFragment.context)
            adapter = BringAreaLocationAdapter(this@TourismFragment, areaDetailTabViewModel)
        }
    }
}