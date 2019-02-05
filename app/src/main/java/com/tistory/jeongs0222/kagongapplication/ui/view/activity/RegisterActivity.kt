package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.YearFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.PersonalInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val registerViewModel by viewModel<RegisterViewModel>()

    private val personalInfoFragment = PersonalInfoFragment()
    private val basicInfoFragment = BasicInfoFragment()
    private val yearFragment = YearFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(basicInfoFragment)

        registerViewModel.bind(
            MessageProviderImpl(this) as MessageProvider,
            IntentProviderImpl(this) as IntentProvider,
            DBHelperProviderImpl(this) as DBHelperProvider)

        registerViewModel.nextClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(personalInfoFragment)
        })

        registerViewModel.previousClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(basicInfoFragment)
        })

        registerViewModel.ageClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(yearFragment)
        })

        registerViewModel.userYear.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(personalInfoFragment)
        })

        viewDataBinding.rViewModel = registerViewModel
        viewDataBinding.setLifecycleOwner(this)
    }


}
