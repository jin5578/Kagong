package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.FragmentAddLocationBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import com.tistory.jeongs0222.kagongapplication.utils.ConstraintSetDynamicProvider
import com.tistory.jeongs0222.kagongapplication.utils.ConstraintSetDynamicProviderImpl
import kotlinx.android.synthetic.main.activity_add_location.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@SuppressLint("ValidFragment")
class AddLocationFragment: Fragment() {

    private val TAG = "AddLocationFragment"

    private lateinit var binding: FragmentAddLocationBinding

    private val addLocationViewModel by sharedViewModel<AddLocationViewModel>()

    private lateinit var constraintSetDynamicProvider: ConstraintSetDynamicProvider


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false).apply {
            alViewModel = addLocationViewModel
            lifecycleOwner = this@AddLocationFragment
        }

        constraintSetDynamicProvider =
                ConstraintSetDynamicProviderImpl(this@AddLocationFragment.activity!!, addLocationViewModel)

        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e("test", "test")

        initView()

        addLocationViewModel.confirmClick.observe(this@AddLocationFragment, Observer {
            /*val tempText: String = binding.scrollViewEntire.getViewById(1).findViewById<TextView>(R.id.departure).text.toString()

            Log.e("tempText", tempText)

            Log.e("sizesize", binding.scrollViewEntire.size.toString())*/
            confirmPreprocessor(binding.scrollViewEntire.size)
        })

        addLocationViewModel.additionClick.observe(this@AddLocationFragment, Observer {
            constraintSetDynamicProvider.createWayAndArrival(binding.scrollViewEntire)
        })

        addLocationViewModel.deleteClick.observe(this@AddLocationFragment, Observer {
            constraintSetDynamicProvider.deleteWayAndArrival(binding.scrollViewEntire)
        })

        addLocationViewModel.selectedLocation.observe(this@AddLocationFragment, Observer {
            if(addLocationViewModel.textPosition == 1) {
                binding.scrollViewEntire.getViewById(addLocationViewModel.textPosition).findViewById<TextView>(R.id.departure).text = it
            } else {
                binding.scrollViewEntire.getViewById(addLocationViewModel.textPosition).findViewById<TextView>(R.id.arrival).text = it
            }

            addLocationViewModel.selectLocationClickEvent()

        })
    }

    private fun initView() {
        constraintSetDynamicProvider.createDeparture(binding.scrollViewEntire)

        constraintSetDynamicProvider.createWayAndArrival(binding.scrollViewEntire)
    }

    private fun confirmPreprocessor(size: Int) {
        val location = arrayListOf<String>()
        val way = arrayListOf<String>()

        for(i in 0 until size) {
            if(i == 0) {
                location.add(binding.scrollViewEntire.getViewById(i+1).findViewById<TextView>(R.id.departure).text.toString())
            } else {
                location.add(binding.scrollViewEntire.getViewById(i+1).findViewById<TextView>(R.id.arrival).text.toString())
                way.add(binding.scrollViewEntire.getViewById(i+1).findViewWithTag<ImageView>(1).transitionName)
            }

        }

        /*for(i in 0 until location.size) {
            Log.e("location array", location[i])
        }

        for(j in 0 until way.size) {
            Log.e("way array", way[j])
        }*/

    }

}