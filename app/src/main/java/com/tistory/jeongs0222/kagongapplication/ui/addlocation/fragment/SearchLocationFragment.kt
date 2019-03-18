package com.tistory.jeongs0222.kagongapplication.ui.addlocation.fragment

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentSearchLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.adapter.FindLocationAdapter
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.AddLocationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@SuppressLint("ValidFragment")
class SearchLocationFragment : Fragment(), TextView.OnEditorActionListener, TextWatcher {

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

        binding.locationSearch.apply {
            setOnEditorActionListener(this@SearchLocationFragment)
            addTextChangedListener(this@SearchLocationFragment)
        }

        binding.locationRecycler.apply {
            layoutManager = LinearLayoutManager(this@SearchLocationFragment.context)
            adapter = FindLocationAdapter(
                this@SearchLocationFragment,
                addLocationViewModel
            )
        }

        addLocationViewModel.bringAreaLocation(0, "")
    }

    override fun onEditorAction(tv: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if (tv!!.id == binding.locationSearch.id && actionId == EditorInfo.IME_ACTION_DONE) {
            postPreprocessor(1, binding.locationSearch.text.toString())
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
        addLocationViewModel.bringAreaLocation(sort, findlocation)

        binding.locationRecycler.adapter =
                FindLocationAdapter(
                    this@SearchLocationFragment,
                    addLocationViewModel
                )
    }
}