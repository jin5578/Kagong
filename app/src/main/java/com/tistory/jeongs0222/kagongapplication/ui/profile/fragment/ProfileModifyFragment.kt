package com.tistory.jeongs0222.kagongapplication.ui.profile.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentProfileModifyBinding
import com.tistory.jeongs0222.kagongapplication.ui.profile.ProfileViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileModifyFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentProfileModifyBinding

    private val profileViewModel by sharedViewModel<ProfileViewModel>()

    private lateinit var permissionProvider: PermissionProvider
    private lateinit var messageProvider: MessageProvider
    private lateinit var intentProvider: IntentProvider
    private lateinit var imm: InputMethodManager



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileModifyBinding.inflate(inflater, container, false).apply {
            pViewModel = profileViewModel
            lifecycleOwner = this@ProfileModifyFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionProvider = PermissionProviderImpl(this@ProfileModifyFragment.activity!!)
        messageProvider = MessageProviderImpl(this@ProfileModifyFragment.activity!!)
        intentProvider = IntentProviderImpl(this@ProfileModifyFragment.activity!!)

        imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.nickname.addTextChangedListener(this@ProfileModifyFragment)

        profileViewModel.validateCheck = false

        profileViewModel.imageClick.observe(this@ProfileModifyFragment, Observer {
            if(permissionProvider.permissionValidate()) {
                getGallery()
            } else {
                permissionAlert()
            }
        })

        profileViewModel.validateClick.observe(this@ProfileModifyFragment, Observer {
            imm.hideSoftInputFromWindow(binding.nickname.windowToken, 0)

            profileViewModel.nicknameValidate(binding.nickname.text.toString())
        })

        profileViewModel.saveClick.observe(this@ProfileModifyFragment, Observer {
            if(profileViewModel.validateCheck)
                profileViewModel.updateProfile(binding.nickname.text.toString(), binding.introduce.text.toString())
            else
                profileViewModel.validateMessage()
        })
    }

    private fun getGallery() {
        intentProvider.intentGallery()
    }

    private fun permissionAlert() {
        messageProvider.settingAlertDialog(1)
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        profileViewModel.validateCheck = false
    }

    override fun afterTextChanged(p0: Editable?) {
        profileViewModel.validateCheck = profileViewModel.nickname.value == p0.toString()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }


}