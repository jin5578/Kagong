package com.tistory.jeongs0222.kagongapplication.ui.appintroduce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.appintroduce.AppIntroduceEventListener


class PictureViewPagerAdapter(
    private val eventListener: AppIntroduceEventListener,
    private val context: Context
) : PagerAdapter() {


    private val images = arrayListOf(R.drawable.app_intro1, R.drawable.app_intro2, R.drawable.app_intro3)
    private val introduce = arrayListOf(R.string.app_introduce1, R.string.app_introduce2, R.string.app_introduce3)

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_picture_image, null)
        val textView = view.findViewById<TextView>(R.id.introduce_item)
        val imageView = view.findViewById<ImageView>(R.id.picture_item)
        val startTextView = view.findViewById<TextView>(R.id.start)

        val viewPager = container as ViewPager

        textView.text = context.getString(introduce[position])

        if(position == 2) {
            startTextView.visibility = View.VISIBLE
        }

        Glide.with(context)
            .asBitmap()
            .load(images[position])
            .apply(
                RequestOptions()
                    .fitCenter()
            )
            .into(imageView)

        startTextView.setOnClickListener {
            eventListener.startClickEvent()
        }

        viewPager.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View

        container.removeView(view)
    }
}