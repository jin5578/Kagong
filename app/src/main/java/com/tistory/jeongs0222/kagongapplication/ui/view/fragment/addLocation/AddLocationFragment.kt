package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.addLocation

import android.annotation.SuppressLint
import android.app.Activity
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
import com.tistory.jeongs0222.kagongapplication.utils.MessageProvider
import com.tistory.jeongs0222.kagongapplication.utils.MessageProviderImpl
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


@SuppressLint("ValidFragment")
class AddLocationFragment: Fragment() {

    private val TAG = "AddLocationFragment"

    private lateinit var binding: FragmentAddLocationBinding

    private val addLocationViewModel by sharedViewModel<AddLocationViewModel>()

    private lateinit var constraintSetDynamicProvider: ConstraintSetDynamicProvider
    private lateinit var  messageProvider : MessageProvider




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false).apply {
            alViewModel = addLocationViewModel
            lifecycleOwner = this@AddLocationFragment
        }

        messageProvider = MessageProviderImpl(this@AddLocationFragment.activity as Activity)

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
            if(checkPreprocessor()) {
                addLocationViewModel.registerLocation(confirmPreprocessor(binding.scrollViewEntire.size))
            } else {
                messageProvider.toastMessage("모든 위치 정보를 입력하세요.")
            }
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

    private fun checkPreprocessor(): Boolean {
        for(i in 0 until binding.scrollViewEntire.size) {
            if(i == 0) {
                if(binding.scrollViewEntire.getViewById(i + 1).findViewById<TextView>(R.id.departure).text.toString() == "출발지 입력") {

                    return false
                }
            } else {
                if(binding.scrollViewEntire.getViewById(i+1).findViewById<TextView>(R.id.arrival).text.toString() == "도착지 입력") {
                    return false
                }
            }
        }

        for(i in 0 until binding.scrollViewEntire.size-1) {
            if(!addLocationViewModel.imageCheck[i]) {
                return false
            }
        }

        return true
    }

    private fun confirmPreprocessor(size: Int): String {
        var location = ""

        for(i in 0 until size) {
            if(i == 0) {
                location += binding.scrollViewEntire.getViewById(i + 1).findViewById<TextView>(R.id.departure).text.toString()
            } else {
                location += ", " + binding.scrollViewEntire.getViewById(i + 1).findViewWithTag<ImageView>(1).transitionName
                location += ", " + binding.scrollViewEntire.getViewById(i + 1).findViewById<TextView>(R.id.arrival).text.toString()
            }
        }

        return location
    }

}