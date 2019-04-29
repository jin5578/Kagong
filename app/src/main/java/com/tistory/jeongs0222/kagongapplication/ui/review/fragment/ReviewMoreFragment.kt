package com.tistory.jeongs0222.kagongapplication.ui.review.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentReviewMoreBinding
import com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter.LocationReviewAdapter
import com.tistory.jeongs0222.kagongapplication.ui.review.ReviewViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ReviewMoreFragment: Fragment() {

    private lateinit var binding: FragmentReviewMoreBinding

    private val viewModel by sharedViewModel<ReviewViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReviewMoreBinding.inflate(inflater, container, false).apply {
            rViewModel = viewModel
            lifecycleOwner = this@ReviewMoreFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@ReviewMoreFragment.context)
            adapter = LocationReviewAdapter(this@ReviewMoreFragment)
        }

        viewModel.bringReview()
    }
}