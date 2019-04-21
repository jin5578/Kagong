package com.tistory.jeongs0222.kagongapplication.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityProfileBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.profile.fragment.ProfileDetailFragment
import com.tistory.jeongs0222.kagongapplication.ui.profile.fragment.ProfileModifyFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileActivity: BaseActivity<ActivityProfileBinding>() {

    override val layoutResourceId: Int = R.layout.activity_profile

    private val profileViewModel by viewModel<ProfileViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val messageProvider = MessageProviderImpl(this@ProfileActivity) as MessageProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@ProfileActivity) as DBHelperProvider
    private val imageCropProvider = ImageCropProviderImpl(this@ProfileActivity) as ImageCropProvider

    private val profileDetailFragment = ProfileDetailFragment()
    private val profileModifyFragment = ProfileModifyFragment()

    private val PICK_FROM_GALLERY = 111
    private val CROP_FROM_CAMERA = 222

    private lateinit var dataUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.initFragment(profileDetailFragment)

        profileViewModel.bind(messageProvider, dbHelperProvider)

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

        profileViewModel.uploadImageValue.observe(this@ProfileActivity, Observer {
            if(it == 0) {
                finish()
            }
        })

        viewDataBinding.pViewModel = profileViewModel
        viewDataBinding.lifecycleOwner = this@ProfileActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == PICK_FROM_GALLERY) {

            if(resultCode == Activity.RESULT_OK) {

                if(data != null) {
                    val temp = data.data!!

                    imageCropProvider.cropImage(temp)
                }

            }

        } else if(requestCode == CROP_FROM_CAMERA) {
            if(data != null) {
                dataUri = data.data!!

                val cropFile = imageCropProvider.galleryAddPic()
                profileViewModel.uploadProfileImage(cropFile)
            }
        }
    }

}