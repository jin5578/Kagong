package com.tistory.jeongs0222.kagongapplication.ui.userprofile

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityUserProfileBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.userprofile.adapter.BringTOPScheduleAdapter
import com.tistory.jeongs0222.kagongapplication.utils.DBHelperProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserProfileActivity: BaseActivity<ActivityUserProfileBinding>() {

    override val layoutResourceId: Int = R.layout.activity_user_profile

    private val viewModel by viewModel<UserProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val theOtherPersonId = intent.getStringExtra("value1")

        viewModel.bind(DBHelperProviderImpl(this@UserProfileActivity), theOtherPersonId)

        viewModel.previousClick.observe(this@UserProfileActivity, Observer {
            finish()
        })

        viewDataBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@UserProfileActivity, 2)
            adapter = BringTOPScheduleAdapter(this@UserProfileActivity, this@UserProfileActivity)
            isNestedScrollingEnabled = false
        }

        viewDataBinding.uViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@UserProfileActivity
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onResume() {
        super.onResume()

        viewModel.userLikeValidate()
    }
}