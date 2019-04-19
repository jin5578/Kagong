package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.tistory.jeongs0222.kagongapplication.R


interface DotsIndicatorProvider {

    fun showDots(dotsCount: Int, dotsLinearLayout: LinearLayout): ArrayList<ImageView>

}

class DotsIndicatorProviderImpl(private val context: Context) : DotsIndicatorProvider {

    override fun showDots(dotsCount: Int, dotsLinearLayout: LinearLayout): ArrayList<ImageView> {
        val dots = ArrayList<ImageView>()

        for (i in 0 until dotsCount) {
            val imageView = ImageView(context)

            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_off))

            dots.add(imageView)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5, 0, 5, 0)

            dotsLinearLayout.addView(dots[i], params)
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_on))

        return dots
    }
}