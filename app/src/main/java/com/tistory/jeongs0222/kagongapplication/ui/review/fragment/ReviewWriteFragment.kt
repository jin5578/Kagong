package com.tistory.jeongs0222.kagongapplication.ui.review.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentReviewWriteBinding
import com.tistory.jeongs0222.kagongapplication.ui.review.ReviewViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ReviewWriteFragment: Fragment() {

    private val TAG = "ReviewWriteFragment"

    private lateinit var binding: FragmentReviewWriteBinding

    private val viewModel by sharedViewModel<ReviewViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReviewWriteBinding.inflate(inflater, container, false).apply {
            rViewModel = viewModel
            lifecycleOwner = this@ReviewWriteFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}