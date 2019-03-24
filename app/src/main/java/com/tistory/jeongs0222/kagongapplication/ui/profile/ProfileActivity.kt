package com.tistory.jeongs0222.kagongapplication.ui.profile

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityProfileBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.profile.fragment.ProfileDetailFragment
import com.tistory.jeongs0222.kagongapplication.ui.profile.fragment.ProfileModifyFragment
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileActivity: BaseActivity<ActivityProfileBinding>() {

    override val layoutResourceId: Int = R.layout.activity_profile

    private val profileViewModel by viewModel<ProfileViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val messageProvider = MessageProviderImpl(this@ProfileActivity) as MessageProvider

    private val profileDetailFragment = ProfileDetailFragment()
    private val profileModifyFragment = ProfileModifyFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(profileDetailFragment)

        profileViewModel.bind(messageProvider)

        profileViewModel.profileDetailPreviousClick.observe(this@ProfileActivity, Observer {
            finish()
        })

        profileViewModel.modifyPreviousClick.observe(this@ProfileActivity, Observer {
            fragmentProvider.replaceFragment(profileDetailFragment)
        })

        profileViewModel.modifyClick.observe(this@ProfileActivity, Observer {
            fragmentProvider.replaceFragment(profileModifyFragment)
        })

        profileViewModel.finishRequest.observe(this@ProfileActivity, Observer {
            fragmentProvider.replaceFragment(profileDetailFragment)
        })

        viewDataBinding.pViewModel = profileViewModel
        viewDataBinding.lifecycleOwner = this@ProfileActivity
    }
}