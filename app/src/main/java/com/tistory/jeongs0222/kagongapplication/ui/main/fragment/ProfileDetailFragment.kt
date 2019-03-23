package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentProfileDetailBinding
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileDetailFragment: Fragment() {

    private lateinit var binding: FragmentProfileDetailBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileDetailBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@ProfileDetailFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(mainViewModel.profileModified) {
            mainViewModel.bringNicknameAndIntro()

            mainViewModel.profileModified = false
        }
    }
}