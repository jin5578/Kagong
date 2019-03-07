package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import kotlinx.android.synthetic.main.activity_add_location.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@SuppressLint("ValidFragment")
class SearchLocationFragment: Fragment() {

    private lateinit var binding: FragmentSearchLocationBinding

    private val addLocationViewModel by sharedViewModel<AddLocationViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchLocationBinding.inflate(inflater, container, false).apply {
            alViewModel = addLocationViewModel
            lifecycleOwner = this@SearchLocationFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}