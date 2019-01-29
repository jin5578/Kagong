package com.tistory.jeongs0222.kagongapplication.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityPersonalInfoFragmentBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PersonalInfoFragment : Fragment() {

    private lateinit var binding: ActivityPersonalInfoFragmentBinding

    private val registerViewModel by sharedViewModel<RegisterViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityPersonalInfoFragmentBinding.inflate(inflater, container, false).apply {
            rViewModel = registerViewModel
            setLifecycleOwner(this@PersonalInfoFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
