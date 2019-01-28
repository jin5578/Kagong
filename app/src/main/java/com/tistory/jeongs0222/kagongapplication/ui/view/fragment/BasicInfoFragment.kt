package com.tistory.jeongs0222.kagongapplication.ui.view.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityBasicInfoFragmentBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel

class BasicInfoFragment : Fragment() {

    private lateinit var binding: ActivityBasicInfoFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityBasicInfoFragmentBinding.inflate(inflater, container, false).apply {
            rViewModel = RegisterViewModel()
            setLifecycleOwner(this@BasicInfoFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
