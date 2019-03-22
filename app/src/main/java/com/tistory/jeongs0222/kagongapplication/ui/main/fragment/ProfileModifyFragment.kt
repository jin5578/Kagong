package com.tistory.jeongs0222.kagongapplication.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentProfileModifyBinding
import com.tistory.jeongs0222.kagongapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileModifyFragment: Fragment() {

    private lateinit var binding: FragmentProfileModifyBinding

    private val mainViewModel by sharedViewModel<MainViewModel>()

    private lateinit var imm: InputMethodManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileModifyBinding.inflate(inflater, container, false).apply {
            mViewModel = mainViewModel
            lifecycleOwner = this@ProfileModifyFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        mainViewModel.validateClick.observe(this@ProfileModifyFragment, Observer {
            imm.hideSoftInputFromWindow(binding.nickname.windowToken, 0)

            mainViewModel.nicknameValidate(binding.nickname.text.toString())
        })
    }
}