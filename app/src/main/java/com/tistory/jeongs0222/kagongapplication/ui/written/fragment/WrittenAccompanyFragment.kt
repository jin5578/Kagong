package com.tistory.jeongs0222.kagongapplication.ui.written.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentWrittenAccompanyBinding
import com.tistory.jeongs0222.kagongapplication.ui.written.WrittenViewModel
import com.tistory.jeongs0222.kagongapplication.ui.written.adapter.WrittenAccompanyAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WrittenAccompanyFragment: Fragment() {

    private val TAG = "WrittenAccompanyFragment"

    private lateinit var binding: FragmentWrittenAccompanyBinding

    private val viewModel by sharedViewModel<WrittenViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWrittenAccompanyBinding.inflate(inflater, container, false).apply {
            wViewModel = viewModel
            lifecycleOwner = this@WrittenAccompanyFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@WrittenAccompanyFragment.context)
            adapter = WrittenAccompanyAdapter(this@WrittenAccompanyFragment, viewModel)
        }
    }
}