package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentTourismBinding
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter.BringAreaLocationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class TourismFragment: Fragment(), TextWatcher {

    private lateinit var binding: FragmentTourismBinding

    private val areaDetailTabViewModel by sharedViewModel<AreaDetailTabViewModel>()

    private lateinit var imm: InputMethodManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTourismBinding.inflate(inflater, container, false).apply {
            tViewModel = areaDetailTabViewModel
            lifecycleOwner = this@TourismFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.locationSearch.addTextChangedListener(this@TourismFragment)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TourismFragment.context)
            adapter = BringAreaLocationAdapter(
                this@TourismFragment,
                areaDetailTabViewModel
            )
        }

        areaDetailTabViewModel.bringAreaLocation(0, "")

        areaDetailTabViewModel.searchClick.observe(this@TourismFragment, Observer {
            imm.hideSoftInputFromWindow(binding.locationSearch.windowToken, 0)

            areaDetailTabViewModel.bringAreaLocation(1, binding.locationSearch.text.toString())

            binding.recyclerView.adapter =
                    BringAreaLocationAdapter(
                        this@TourismFragment,
                        areaDetailTabViewModel
                    )
        })

    }

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(charSequence!!.isEmpty()) {
            areaDetailTabViewModel.bringAreaLocation(0, "")

            binding.recyclerView.adapter =
                    BringAreaLocationAdapter(
                        this@TourismFragment,
                        areaDetailTabViewModel
                    )
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

}