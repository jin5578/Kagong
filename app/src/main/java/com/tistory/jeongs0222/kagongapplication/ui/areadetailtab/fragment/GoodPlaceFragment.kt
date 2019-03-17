package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentGoodPlaceBinding
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter.BringAreaGoodPlaceAdapter
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.AreaDetailTabViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GoodPlaceFragment: Fragment(), TextWatcher, TextView.OnEditorActionListener {

    private lateinit var binding: FragmentGoodPlaceBinding

    private val areaDetailTabViewModel by sharedViewModel<AreaDetailTabViewModel>()

    private lateinit var imm: InputMethodManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGoodPlaceBinding.inflate(inflater, container, false).apply {
            tViewModel = areaDetailTabViewModel
            lifecycleOwner = this@GoodPlaceFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imm = view.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.goodPlaceSearch.apply {
            setOnEditorActionListener(this@GoodPlaceFragment)
            addTextChangedListener(this@GoodPlaceFragment)

        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@GoodPlaceFragment.context)
            adapter = BringAreaGoodPlaceAdapter(
                this@GoodPlaceFragment,
                areaDetailTabViewModel
            )
        }

        areaDetailTabViewModel.bringAreaGoodPlace(0, "")

        areaDetailTabViewModel.searchClick.observe(this@GoodPlaceFragment, Observer {
            imm.hideSoftInputFromWindow(binding.goodPlaceSearch.windowToken, 0)

            postPreprocessor(1, binding.goodPlaceSearch.text.toString())
        })

    }

    override fun onEditorAction(tv: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if (tv!!.id == binding.goodPlaceSearch.id && actionId == EditorInfo.IME_ACTION_DONE) {
            postPreprocessor(1, binding.goodPlaceSearch.text.toString())
        }

        return false
    }

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (charSequence!!.isEmpty()) {
            postPreprocessor(0, "")
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    private fun postPreprocessor(sort: Int, findlocation: String) {
        areaDetailTabViewModel.bringAreaGoodPlace(sort, findlocation)

        binding.recyclerView.adapter =
                BringAreaGoodPlaceAdapter(
                    this@GoodPlaceFragment,
                    areaDetailTabViewModel
                )
    }
}