package com.tistory.jeongs0222.kagongapplication.ui.googleMap

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.maps.*
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityGoogleMapBinding
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class GoogleMapActivity: BaseActivity<ActivityGoogleMapBinding>(), OnMapReadyCallback {

    override val layoutResourceId: Int = R.layout.activity_google_map

    private val viewModel by viewModel<GoogleMapViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order: Int = intent.getIntExtra("value1", 0)

        viewModel.locationItem.observe(this@GoogleMapActivity, Observer {
            val mapFragment: SupportMapFragment? = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            mapFragment?.getMapAsync(this@GoogleMapActivity)
        })

        viewModel.locationMap(order)


        viewDataBinding.gViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@GoogleMapActivity
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        viewModel.onMapReady(googleMap)
    }

    override fun onBackPressed() {
        finish()
    }

}