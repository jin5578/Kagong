package com.tistory.jeongs0222.kagongapplication.ui.register.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentBasicInfoBinding
import com.tistory.jeongs0222.kagongapplication.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class BasicInfoFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentBasicInfoBinding

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    private lateinit var imm: InputMethodManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBasicInfoBinding.inflate(inflater, container, false).apply {
            rViewModel = registerViewModel
            lifecycleOwner = this@BasicInfoFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.nickname.addTextChangedListener(this@BasicInfoFragment)

        registerViewModel.validateClick.observe(this@BasicInfoFragment, Observer {
            imm.hideSoftInputFromWindow(binding.nickname.windowToken, 0)

            registerViewModel.nicknameValidate(binding.nickname.text.toString())
        })
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        registerViewModel.validateCheck = false
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}
