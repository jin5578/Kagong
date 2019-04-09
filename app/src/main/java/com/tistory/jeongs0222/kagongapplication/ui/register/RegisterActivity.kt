package com.tistory.jeongs0222.kagongapplication.ui.register

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.register.fragment.YearFragment
import com.tistory.jeongs0222.kagongapplication.ui.register.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.register.fragment.PersonalInfoFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class    RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val registerViewModel by viewModel<RegisterViewModel>()

    private val personalInfoFragment = PersonalInfoFragment()
    private val basicInfoFragment = BasicInfoFragment()
    private val yearFragment = YearFragment()

    private lateinit var userKey: String
    private lateinit var loginMethod: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userKey = intent.getStringExtra("value1")
        Log.e("userKey", userKey)
        loginMethod = intent.getStringExtra("value2")

        fragmentProvider.initFragment(basicInfoFragment)

        registerViewModel.bind(
            MessageProviderImpl(this) as MessageProvider,
            IntentProviderImpl(this) as IntentProvider,
            DBHelperProviderImpl(this) as DBHelperProvider,
            userKey,
            loginMethod
        )

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
        viewDataBinding.lifecycleOwner = this@RegisterActivity
    }
}
