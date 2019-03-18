package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchAreaBinding
import com.tistory.jeongs0222.kagongapplication.ui.main.adapter.AreaSearchHistoryAdapter
import com.tistory.jeongs0222.kagongapplication.ui.main.adapter.FindAreaAdapter
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SearchAreaFragment : Fragment(), TextView.OnEditorActionListener, TextWatcher {

    private lateinit var binding: FragmentSearchAreaBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchAreaBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@SearchAreaFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.areaSearch.apply {
            setOnEditorActionListener(this@SearchAreaFragment)
            addTextChangedListener(this@SearchAreaFragment)
        }
        binding.areaSearch.setOnEditorActionListener(this@SearchAreaFragment)

        binding.recentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchAreaFragment.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = AreaSearchHistoryAdapter(
                this@SearchAreaFragment,
                mainViewModel
            )
        }

        binding.findRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchAreaFragment.context)
            adapter = FindAreaAdapter(
                this@SearchAreaFragment,
                mainViewModel
            )
        }

        mainViewModel.findArea("", 0)

        mainViewModel.selectedHistoryClick.observe(this@SearchAreaFragment, Observer {
            mainViewModel.findAreaLog()
        })

        mainViewModel.selectedSearchClick.observe(this@SearchAreaFragment, Observer {
            mainViewModel.findAreaLog()
        })
    }

    override fun onEditorAction(tv: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if(tv!!.id == binding.areaSearch.id && actionId == EditorInfo.IME_ACTION_DONE) {
            postPreprocessor(binding.areaSearch.text.toString(), 1)
        }

        return false
    }

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(charSequence!!.isEmpty()) {
            postPreprocessor("", 0)
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    private fun postPreprocessor(findcontent: String, sort: Int) {
        mainViewModel.findArea(findcontent, sort)

        binding.findRecyclerView.adapter =
                FindAreaAdapter(
                    this@SearchAreaFragment,
                    mainViewModel
                )
    }
}