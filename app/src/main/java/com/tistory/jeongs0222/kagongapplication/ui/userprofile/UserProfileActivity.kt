package com.tistory.jeongs0222.kagongapplication.ui.userprofile

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityUserProfileBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserProfileActivity: BaseActivity<ActivityUserProfileBinding>() {

    override val layoutResourceId: Int = R.layout.activity_user_profile

    private val viewModel by viewModel<UserProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.previousClick.observe(this@UserProfileActivity, Observer {
            finish()
        })

        viewDataBinding.uViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@UserProfileActivity
    }

    override fun onBackPressed() {
        finish()
    }
}