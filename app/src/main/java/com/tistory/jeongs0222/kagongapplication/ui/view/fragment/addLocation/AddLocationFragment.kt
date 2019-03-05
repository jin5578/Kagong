package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentAddLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AddLocationFragment: Fragment() {

    private val TAG = "AddLocationFragment"

    private lateinit var binding: FragmentAddLocationBinding

    private val addLocationViewModel by sharedViewModel<AddLocationViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false).apply {
            alViewModel = addLocationViewModel
            lifecycleOwner = this@AddLocationFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addLocationViewModel.additionClick.observe(this@AddLocationFragment, Observer {

        })
    }

}