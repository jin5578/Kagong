package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailtabfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailTabViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val areaDetailTabViewModel by sharedViewModel<AreaDetailTabViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            tViewModel = areaDetailTabViewModel
            lifecycleOwner = this@SearchFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
