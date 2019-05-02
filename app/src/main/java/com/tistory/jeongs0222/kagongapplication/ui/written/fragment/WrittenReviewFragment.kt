package com.tistory.jeongs0222.kagongapplication.ui.written.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentWrittenReviewBinding
import com.tistory.jeongs0222.kagongapplication.ui.written.WrittenViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WrittenReviewFragment: Fragment() {

    private val TAG = "WrittenReviewFragment"

    private lateinit var binding: FragmentWrittenReviewBinding

    private val viewModel by sharedViewModel<WrittenViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWrittenReviewBinding.inflate(inflater, container, false).apply {
            wViewModel = viewModel
            lifecycleOwner = this@WrittenReviewFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}
