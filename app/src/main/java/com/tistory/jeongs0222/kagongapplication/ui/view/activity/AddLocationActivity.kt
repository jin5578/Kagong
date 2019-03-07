package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation.AddLocationFragment
import com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation.SearchLocationFragment
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddLocationActivity: BaseActivity<ActivityAddLocationBinding>() {

    override val layoutResourceId: Int = R.layout.activity_add_location

    private val addLocationViewModel by viewModel<AddLocationViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val addLocationFragment = AddLocationFragment()
    private val searchLocationFragment = SearchLocationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentProvider.addLocationFragment(0, addLocationFragment)
        fragmentProvider.addLocationFragment(1, searchLocationFragment)

        addLocationViewModel.previousClick.observe(this@AddLocationActivity, Observer {
            finish()
        })

        viewDataBinding.alViewModel = addLocationViewModel
        viewDataBinding.lifecycleOwner = this@AddLocationActivity
    }

    override fun onBackPressed() {
        finish()
    }
}