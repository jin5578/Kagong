package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.BuildConfig
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentProfileBinding
import com.tistory.jeongs0222.kagongapplication.ui.howToUse.HowToUseActivity
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileFragment: Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    private lateinit var intentProvider: IntentProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@ProfileFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentProvider = IntentProviderImpl(this@ProfileFragment.activity!!)

        mainViewModel.howToUseClick.observe(this@ProfileFragment, Observer {
            intentProvider.intent(HowToUseActivity::class.java)
        })

        mainViewModel.reviewClick.observe(this@ProfileFragment, Observer {
            intentProvider.intentReview()
        })



    }

}