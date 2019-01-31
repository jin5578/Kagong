package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.YearFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.PersonalInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.UserSexProvider
import com.tistory.jeongs0222.kagongapplication.utils.UserSexProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val userSexProvider = UserSexProviderImpl(this) as UserSexProvider

    private val registerViewModel by viewModel<RegisterViewModel>()

    private val personalInfoFragment = PersonalInfoFragment()
    private val basicInfoFragment = BasicInfoFragment()
    private val yearFragment = YearFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment()

        registerViewModel.nextClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(personalInfoFragment)
        })

        registerViewModel.previousClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(basicInfoFragment)
        })

        registerViewModel.ageClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.addFragment(yearFragment)
        })

        registerViewModel.userSex.observe(this@RegisterActivity, Observer {
            userSexProvider.sexChange(it)
        })

        registerViewModel.userYear.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(personalInfoFragment)
        })

        viewDataBinding.rViewModel = registerViewModel
        viewDataBinding.setLifecycleOwner(this)
    }


}
