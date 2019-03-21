package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAccompanyWriteBinding
import com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter.CategoryAdapter
import com.tistory.jeongs0222.kagongapplication.utils.IntentProvider
import com.tistory.jeongs0222.kagongapplication.utils.IntentProviderImpl
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccompanyWriteActivity: BaseActivity<ActivityAccompanyWriteBinding>() {


    override val layoutResourceId: Int = R.layout.activity_accompany_write

    private val accompanyWriteViewModel by viewModel<AccompanyWriteViewModel>()

    private lateinit var area: String

    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val intentProvider = IntentProviderImpl(this@AccompanyWriteActivity) as IntentProvider


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        sheetBehavior = BottomSheetBehavior.from(viewDataBinding.include.bottomSheet)
        sheetBehavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, p1: Int) {
            }
        })

        viewDataBinding.include.calendar.setOnDateChangeListener { p0, p1, p2, p3 ->
            if((p2+1) < 10) {
                if(p3 < 10) {
                    accompanyWriteViewModel.calendarSelected(p1.toString() + "-" + "0" + (p2+1).toString() + "-" + "0" + p3.toString())
                } else {
                    accompanyWriteViewModel.calendarSelected(p1.toString() + "-" + "0" + (p2+1).toString() + "-" + p3.toString())
                }
            } else {
                if(p3 < 10) {
                    accompanyWriteViewModel.calendarSelected(p1.toString() + "-" + (p2+1).toString() + "-" + "0" + p3.toString())
                } else {
                    accompanyWriteViewModel.calendarSelected(p1.toString() + "-" + (p2+1).toString() + "-" + p3.toString())
                }
            }
        }

        viewDataBinding.include.boardRecycler.apply {
            layoutManager = GridLayoutManager(this@AccompanyWriteActivity, 5)
            adapter = CategoryAdapter(this@AccompanyWriteActivity, accompanyWriteViewModel)
        }

        accompanyWriteViewModel.bind(area, intentProvider)

        accompanyWriteViewModel.previousClick.observe(this@AccompanyWriteActivity, Observer {
            finish()
        })

        accompanyWriteViewModel.confirmClick.observe(this@AccompanyWriteActivity, Observer {

        })

        viewDataBinding.awViewModel = accompanyWriteViewModel
        viewDataBinding.lifecycleOwner = this@AccompanyWriteActivity
    }

    override fun onBackPressed() {
        finish()
    }
}