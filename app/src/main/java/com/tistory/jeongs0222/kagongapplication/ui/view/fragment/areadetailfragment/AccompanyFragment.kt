package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.AccompanyListAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AreaDetailViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AccompanyFragment: Fragment(){

    private val TAG = "AccompanyFragment"

    private lateinit var binding: FragmentAccompanyBinding

    private val areaDetailViewModel by sharedViewModel<AreaDetailViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAccompanyBinding.inflate(inflater, container, false).apply {
            dViewModel = areaDetailViewModel
            setLifecycleOwner(this@AccompanyFragment)
        }

        Log.e(TAG, "onCreateView")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "onViewCreated")

        areaDetailViewModel.bringAccompanyList()

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AccompanyFragment.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AccompanyListAdapter(this@AccompanyFragment, areaDetailViewModel)
        }
    }

}