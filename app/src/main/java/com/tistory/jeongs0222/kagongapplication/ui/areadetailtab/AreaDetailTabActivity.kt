package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAreaDetailTabBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment.AccompanyFragment
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment.GoodPlaceFragment
import com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.fragment.TourismFragment
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProvider
import com.tistory.jeongs0222.kagongapplication.utils.FragmentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class AreaDetailTabActivity : BaseActivity<ActivityAreaDetailTabBinding>() {

    private val TAG = "AreaDetailTabActivity"

    override val layoutResourceId: Int = R.layout.activity_area_detail_tab

    private val areaDetailTabViewModel by viewModel<AreaDetailTabViewModel>()

    private val fragmentProvider = FragmentProviderImpl(supportFragmentManager) as FragmentProvider

    private val tourismFragment =
        TourismFragment()
    private val goodPlaceFragment =
        GoodPlaceFragment()
    private val accompanyFragment =
        AccompanyFragment()

    private lateinit var area: String
    private lateinit var order: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")
        order = intent.getStringExtra("order")

        initView()

        areaDetailTabViewModel.bind(area)

        areaDetailTabViewModel.previousClick.observe(this@AreaDetailTabActivity, Observer {
            finish()
        })

        viewDataBinding.tViewModel = areaDetailTabViewModel
        viewDataBinding.lifecycleOwner = this@AreaDetailTabActivity
    }

    private fun initView() {
        when(order) {
            "1" -> fragmentProvider.replaceFragment(tourismFragment)

            "2" -> fragmentProvider.replaceFragment(goodPlaceFragment)

            "3" -> fragmentProvider.replaceFragment(accompanyFragment)
        }
    }

    override fun onBackPressed() {
        finish()
    }

}