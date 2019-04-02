package com.tistory.jeongs0222.kagongapplication.ui.register.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentPersonalInfoBinding
import com.tistory.jeongs0222.kagongapplication.ui.register.RegisterViewModel
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.UserSexProvider
import com.tistory.jeongs0222.kagongapplication.utils.UserSexProviderImpl
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class PersonalInfoFragment : Fragment() {

    private val TAG = "PersonalInfoFragment"

    private lateinit var binding: FragmentPersonalInfoBinding

    private val registerViewModel by sharedViewModel<RegisterViewModel>()

    private lateinit var userSexProvider: UserSexProvider
    private lateinit var messageProvider: MessageProvider


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false).apply {
            rViewModel = registerViewModel
            lifecycleOwner = this@PersonalInfoFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userSexProvider = UserSexProviderImpl(this@PersonalInfoFragment.activity!!)
        messageProvider = MessageProviderImpl(this@PersonalInfoFragment.activity!!)

        registerViewModel.userSex.observe(this@PersonalInfoFragment, Observer {
            userSexProvider.sexChange(it)
        })

        registerViewModel.confirmClick.observe(this@PersonalInfoFragment, Observer {
            if(registerViewModel.validateCheck) {
                if(!registerViewModel.userSex.value.isNullOrBlank()) {
                    if(!registerViewModel.userYear.value.isNullOrBlank()) {
                        registerViewModel
                            .register(
                                registerViewModel.userNickname.value!!,
                                registerViewModel.userSex.value!!,
                                registerViewModel.userYear.value!!)
                    } else {
                        messageProvider.toastMessage(view.context.getString(R.string.please_select_age))
                    }
                } else {
                    messageProvider.toastMessage(view.context.getString(R.string.please_select_sex))
                }
            } else {
                messageProvider.toastMessage(view.context.getString(R.string.validate_null))
            }
        })
    }
}
