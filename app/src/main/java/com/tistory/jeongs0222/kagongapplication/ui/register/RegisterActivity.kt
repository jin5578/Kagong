package com.tistory.jeongs0222.kagongapplication.ui.register

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.register.fragment.YearFragment
import com.tistory.jeongs0222.kagongapplication.ui.register.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class    RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    private val registerViewModel by viewModel<RegisterViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val basicInfoFragment = BasicInfoFragment()
    private val yearFragment = YearFragment()

    private lateinit var userKey: String
    private lateinit var loginMethod: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userKey = intent.getStringExtra("value1")
        loginMethod = intent.getStringExtra("value2")

        fragmentProvider.initFragment(basicInfoFragment)

        registerViewModel.bind(
            MessageProviderImpl(this) as MessageProvider,
            IntentProviderImpl(this) as IntentProvider,
            DBHelperProviderImpl(this) as DBHelperProvider,
            userKey,
            loginMethod
        )

        registerViewModel.ageClick.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(yearFragment)
        })

        registerViewModel.userYear.observe(this@RegisterActivity, Observer {
            fragmentProvider.replaceFragment(basicInfoFragment)
        })

        viewDataBinding.rViewModel = registerViewModel
        viewDataBinding.lifecycleOwner = this@RegisterActivity
    }

    override fun onBackPressed() {
        this@RegisterActivity.apply {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        }

    }
}
