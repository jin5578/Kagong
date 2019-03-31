package com.tistory.jeongs0222.kagongapplication.ui.profile.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentProfileModifyBinding
import com.tistory.jeongs0222.kagongapplication.ui.profile.ProfileViewModel
import com.tistory.jeongs0222.kagongapplication.utils.ImageCropProvider
import com.tistory.jeongs0222.kagongapplication.utils.ImageCropProviderImpl
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileModifyFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentProfileModifyBinding

    private val profileViewModel by sharedViewModel<ProfileViewModel>()

    private lateinit var imageCropProvider: ImageCropProvider
    private lateinit var imm: InputMethodManager

    private val PICK_FROM_GALLERY = 111
    private val CROP_FROM_CAMERA = 222

    private lateinit var dataUri: Uri


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileModifyBinding.inflate(inflater, container, false).apply {
            pViewModel = profileViewModel
            lifecycleOwner = this@ProfileModifyFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageCropProvider = ImageCropProviderImpl(this@ProfileModifyFragment)

        imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.nickname.addTextChangedListener(this@ProfileModifyFragment)

        profileViewModel.validateCheck = false

        profileViewModel.imageClick.observe(this@ProfileModifyFragment, Observer {
            if (ContextCompat.checkSelfPermission(this@ProfileModifyFragment.context!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this@ProfileModifyFragment.context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getGallery()
            } else {
                permissionAlert()
            }
        })

        profileViewModel.validateClick.observe(this@ProfileModifyFragment, Observer {
            imm.hideSoftInputFromWindow(binding.nickname.windowToken, 0)

            profileViewModel.nicknameValidate(binding.nickname.text.toString())
        })

        profileViewModel.saveClick.observe(this@ProfileModifyFragment, Observer {
            if(profileViewModel.validateCheck)
                profileViewModel.updateProfile(binding.nickname.text.toString(), binding.introduce.text.toString())
            else
                profileViewModel.validateMessage()
        })
    }

    @SuppressLint("IntentReset")
    private fun getGallery() {
        val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE

        startActivityForResult(intent, PICK_FROM_GALLERY)
    }

    private fun permissionAlert() {
        Log.e("123", "123")
        AlertDialog.Builder(context)
            .apply {
                setTitle("알림")
                setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                setNeutralButton("설정") { dialogInterface, which ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.parse("package:" + context.packageName)

                    startActivity(intent)

                    Log.e("456", "456")
                }
                setCancelable(false)
            }
            .create()
            .show()
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.e("onTextChanged", "called")
        profileViewModel.validateCheck = false
    }

    override fun afterTextChanged(p0: Editable?) {
        profileViewModel.validateCheck = profileViewModel.nickname.value == p0.toString()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
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