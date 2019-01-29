package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityRegisterBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.BasicInfoFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.RegisterViewModel
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.ViewPagerProvider
import com.tistory.jeongs0222.kagongapplication.utils.ViewPagerProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val TAG = "RegisterActivity"

    override val layoutResourceId: Int = R.layout.activity_register

    //private val viewPagerProvider = ViewPagerProviderImpl(supportFragmentManager) as ViewPagerProvider
    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val registerViewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel.bind(fragmentProvider)

        registerViewModel.test1.observe(this@RegisterActivity, Observer {
            Log.e(TAG, "activity observer")
            registerViewModel.replaceFragment()
        })

        registerViewModel.test2.observe(this@RegisterActivity, Observer {
            registerViewModel.replaceFragment()
        })



        /*val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, BasicInfoFragment()).commit()*/


        //registerViewModel.bind(viewPagerProvider)

        viewDataBinding.rViewModel = registerViewModel
        viewDataBinding.setLifecycleOwner(this)
    }
}
