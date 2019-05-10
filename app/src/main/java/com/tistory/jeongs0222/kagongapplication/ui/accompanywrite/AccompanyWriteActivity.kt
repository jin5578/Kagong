package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAccompanyWriteBinding
import com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter.CategoryAdapter
import com.tistory.jeongs0222.kagongapplication.ui.howToUse.HowToUseActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AccompanyWriteActivity : BaseActivity<ActivityAccompanyWriteBinding>(), TextView.OnEditorActionListener {

    override val layoutResourceId: Int = R.layout.activity_accompany_write

    private val viewModel by viewModel<AccompanyWriteViewModel>()

    private lateinit var area: String

    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val intentProvider = IntentProviderImpl(this@AccompanyWriteActivity) as IntentProvider
    private val messageProvider = MessageProviderImpl(this@AccompanyWriteActivity) as MessageProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@AccompanyWriteActivity) as DBHelperProvider


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        area = intent.getStringExtra("area")

        sheetBehavior = BottomSheetBehavior.from(viewDataBinding.include.bottomSheet)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(p0: View, p1: Float) {
            }

            override fun onStateChanged(p0: View, p1: Int) {
            }
        })

        viewDataBinding.include.editLink.setOnEditorActionListener(this@AccompanyWriteActivity)

        viewDataBinding.include.calendar.setOnDateChangeListener { p0, p1, p2, p3 ->
            viewModel.calendarSelected(p1, p2, p3)
        }

        viewDataBinding.include.boardRecycler.apply {
            layoutManager = GridLayoutManager(this@AccompanyWriteActivity, 5)
            adapter = CategoryAdapter(this@AccompanyWriteActivity, viewModel)
        }

        viewModel.bind(area, intentProvider, messageProvider, dbHelperProvider)

        viewModel.previousClick.observe(this@AccompanyWriteActivity, Observer {
            finish()
        })

        viewModel.howToUseClick.observe(this@AccompanyWriteActivity, Observer {
            intentProvider.intent(HowToUseActivity::class.java)
        })

        viewModel.confirmClick.observe(this@AccompanyWriteActivity, Observer {
            if (viewDataBinding.content.text.isNotEmpty()
                && !viewModel.selectedCategory.value.isNullOrBlank()
                && !viewModel.selectedDate.value.isNullOrBlank()
                && !viewModel.selectedLink.value.isNullOrBlank()
                && viewModel.selectedLink.value!!.contains("https://open.kakao.com/")
            ) {
                viewModel.accompanyWrite(
                    viewDataBinding.content.text.toString()
                )
            } else {
                messageProvider.toastMessage(resources.getString(R.string.without_spaces))
            }
        })

        viewDataBinding.awViewModel = viewModel
        viewDataBinding.lifecycleOwner = this@AccompanyWriteActivity
    }

    override fun onEditorAction(tv: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
        if (tv!!.id == viewDataBinding.include.editLink.id && actionId == EditorInfo.IME_ACTION_DONE) {
            if(viewDataBinding.include.editLink.text.contains("https://open.kakao.com/")) {
                viewModel.linkSelected(viewDataBinding.include.editLink.text.toString())
            } else {
                messageProvider.toastMessage(resources.getString(R.string.not_validate_address))
            }
        }

        return false
    }


    override fun onBackPressed() {
        finish()
    }
}