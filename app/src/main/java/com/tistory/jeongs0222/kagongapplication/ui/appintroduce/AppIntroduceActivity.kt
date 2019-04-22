package com.tistory.jeongs0222.kagongapplication.ui.appintroduce

import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.BaseActivity
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAppIntroduceBinding
import com.tistory.jeongs0222.kagongapplication.ui.appintroduce.adapter.PictureViewPagerAdapter
import com.tistory.jeongs0222.kagongapplication.ui.login.LoginActivity
import com.tistory.jeongs0222.kagongapplication.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class AppIntroduceActivity: BaseActivity<ActivityAppIntroduceBinding>(), ViewPager.OnPageChangeListener {

    override val layoutResourceId: Int = R.layout.activity_app_introduce

    private val appIntroduceViewModel by viewModel<AppIntroduceViewModel>()

    private val intentProvider = IntentProviderImpl(this@AppIntroduceActivity) as IntentProvider
    private val dotsIndicatorProvider = DotsIndicatorProviderImpl(this@AppIntroduceActivity) as DotsIndicatorProvider
    private val dbHelperProvider = DBHelperProviderImpl(this@AppIntroduceActivity)

    private lateinit var dotsImage: ArrayList<ImageView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(dbHelperProvider.getDBHelper().getUserKey().isNotEmpty()) {
            intentProvider.finishIntent(LoginActivity::class.java)
        }

        viewDataBinding.viewPager.apply {
            adapter = PictureViewPagerAdapter(this@AppIntroduceActivity, appIntroduceViewModel)
            addOnPageChangeListener(this@AppIntroduceActivity)
        }

        dotsImage = dotsIndicatorProvider.showDots(3, viewDataBinding.detailDotsLinearLayout)

        appIntroduceViewModel.startClick.observe(this@AppIntroduceActivity, Observer {
            intentProvider.finishIntent(LoginActivity::class.java)
        })

        viewDataBinding.aiViewModel = appIntroduceViewModel
        viewDataBinding.lifecycleOwner = this@AppIntroduceActivity
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        for(j in 0 until dotsImage.size) {
            dotsImage[j].setImageDrawable(ContextCompat.getDrawable(this@AppIntroduceActivity, R.drawable.dot_off))
        }
        dotsImage[position].setImageDrawable(ContextCompat.getDrawable(this@AppIntroduceActivity, R.drawable.dot_on))
    }

    override fun onBackPressed() {
        this@AppIntroduceActivity.apply {
            moveTaskToBack(true)
            finishAffinity()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}