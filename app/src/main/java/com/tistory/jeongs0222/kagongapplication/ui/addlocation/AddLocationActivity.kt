package com.tistory.jeongs0222.kagongapplication.ui.addlocation

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.fragment.AddLocationFragment
import com.tistory.jeongs0222.kagongapplication.ui.addlocation.fragment.SearchLocationFragment
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddLocationActivity: BaseActivity<ActivityAddLocationBinding>() {

    private val TAG = "AddLocationActivity"

    override val layoutResourceId: Int = R.layout.activity_add_location

    private val addLocationViewModel by viewModel<AddLocationViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider
    private val messageProvider = MessageProviderImpl(this@AddLocationActivity) as MessageProvider
    private val intentProvider = IntentProviderImpl(this@AddLocationActivity) as IntentProvider

    private val addLocationFragment =
        AddLocationFragment()
    private val searchLocationFragment =
        SearchLocationFragment()

    private lateinit var area: String
    private lateinit var sort: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")
        sort = intent.getStringExtra("order")

        fragmentProvider.addLocationFragment(0, addLocationFragment)
        fragmentProvider.addLocationFragment(1, searchLocationFragment)

        addLocationViewModel.bind(area, sort, messageProvider, intentProvider)

        addLocationViewModel.previousClick.observe(this@AddLocationActivity, Observer {
            finish()
        })

       viewDataBinding.alViewModel = addLocationViewModel
        viewDataBinding.lifecycleOwner = this@AddLocationActivity
    }

    override fun onBackPressed() {
        addLocationViewModel.previousClickEvent()
    }
}