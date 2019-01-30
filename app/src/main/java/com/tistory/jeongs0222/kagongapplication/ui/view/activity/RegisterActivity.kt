package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.AgeFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.PersonalInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.UserSexProvider
import com.tistory.jeongs0222.kagongapplication.utils.UserSexProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private var FRAGMENT_ID: Fragment = PersonalInfoFragment()

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val userSexProvider = UserSexProviderImpl(this) as UserSexProvider

    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment()

        registerViewModel.nextClick.observe(this@RegisterActivity, Observer {
            FRAGMENT_ID = PersonalInfoFragment()

            fragmentProvider.replaceFragment(FRAGMENT_ID)
        })

        registerViewModel.previousClick.observe(this@RegisterActivity, Observer {
            FRAGMENT_ID = BasicInfoFragment()

            fragmentProvider.replaceFragment(FRAGMENT_ID)
        })

        registerViewModel.ageClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.addFragment(AgeFragment())
        })

        registerViewModel.userSex.observe(this@RegisterActivity, Observer {
            userSexProvider.sexChange(it)
        })

        viewDataBinding.rViewModel = registerViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
