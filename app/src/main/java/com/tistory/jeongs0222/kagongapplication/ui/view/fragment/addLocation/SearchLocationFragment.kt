package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.adapter.FindLocationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@SuppressLint("ValidFragment")
class SearchLocationFragment: Fragment(), TextWatcher {
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

        binding.locationSearch.addTextChangedListener(this@SearchLocationFragment)

        binding.locationRecycler.apply {
            layoutManager = LinearLayoutManager(this@SearchLocationFragment.context)
            adapter = FindLocationAdapter(this@SearchLocationFragment, addLocationViewModel)
        }
    }

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        addLocationViewModel.findLocation(charSequence!!)
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

}