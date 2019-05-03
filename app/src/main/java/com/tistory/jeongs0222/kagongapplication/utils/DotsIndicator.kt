
package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context
import android.database.DataSetObserver
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.viewpager.widget.ViewPager
import com.tistory.jeongs0222.kagongapplication.R
import java.util.ArrayList


class DotsIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val strokeDots: MutableList<ImageView>?
    private var dotIndicatorView: ImageView? = null
    private var dotIndicatorLayout: View? = null
    private var viewPager: ViewPager? = null

    // Attributes
    private var dotsSize: Int = 0
    private var dotsSpacing: Int = 0
    private var dotsStrokeWidth: Int = 0
    private var dotsCornerRadius: Int = 0
    private var dotIndicatorColor: Int = 0
    private var dotsStrokeColor: Int = 0

    private val horizontalMargin: Int
    private var dotIndicatorXSpring: SpringAnimation? = null
    private var dotIndicatorWidthSpring: SpringAnimation? = null
    private val strokeDotsLinearLayout: LinearLayout

    private var dotsClickable: Boolean = false
    private var pageChangedListener: ViewPager.OnPageChangeListener? = null

    init {
        strokeDots = ArrayList()

        strokeDotsLinearLayout = LinearLayout(context)

        val linearParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        horizontalMargin = dpToPx(24)
        linearParams.setMargins(horizontalMargin, 0, horizontalMargin, 0)

        strokeDotsLinearLayout.layoutParams = linearParams
        strokeDotsLinearLayout.orientation = HORIZONTAL

        addView(strokeDotsLinearLayout)

        dotsSize = dpToPx(7) // 9dp
        dotsSpacing = dpToPx(4) // 4dp
        dotsStrokeWidth = dpToPx(1) // 1dp
        dotsCornerRadius = dotsSize / 2
        dotIndicatorColor = getThemePrimaryColor(context)
        dotsStrokeColor = dotIndicatorColor
        dotsClickable = true


        if (attrs != null) {
            val a = getContext().obtainStyledAttributes(attrs, R.styleable.DotsIndicator)

            // Dots attributes
            dotIndicatorColor = a.getColor(R.styleable.DotsIndicator_dotsColor, dotIndicatorColor)
            dotsStrokeColor = a.getColor(R.styleable.DotsIndicator_dotsStrokeColor, dotIndicatorColor)
            dotsSize = a.getDimension(R.styleable.DotsIndicator_dotsSize, dotsSize.toFloat()).toInt()
            dotsSpacing = a.getDimension(R.styleable.DotsIndicator_dotsSpacing, dotsSpacing.toFloat()).toInt()
            dotsCornerRadius =
                a.getDimension(R.styleable.DotsIndicator_dotsCornerRadius, (dotsSize / 2).toFloat()).toInt()

            // Spring dots attributes
            dotsStrokeWidth =
                a.getDimension(R.styleable.DotsIndicator_dotsStrokeWidth, dotsStrokeWidth.toFloat()).toInt()

            a.recycle()
        }

        if (isInEditMode) {
            addStrokeDots(5)
            addView(buildDot(false))
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        refreshDots()
    }

    private fun refreshDots() {
        if (dotIndicatorLayout == null) {
            setUpDotIndicator()
        }

        if (viewPager != null && viewPager!!.adapter != null) {
            if (strokeDots!!.size < viewPager!!.adapter!!.count) {
                addStrokeDots(viewPager!!.adapter!!.count - strokeDots.size)
            } else if (strokeDots.size > viewPager!!.adapter!!.count) {
                removeDots(strokeDots.size - viewPager!!.adapter!!.count)
            }
            setUpDotsAnimators()
        } else {

        }
    }

    private fun setUpDotIndicator() {
        if (viewPager != null && viewPager!!.adapter != null && viewPager!!.adapter!!.count == 0) {
            return
        }

        if (dotIndicatorView != null && indexOfChild(dotIndicatorView) != -1) {
            removeView(dotIndicatorView)
        }

        dotIndicatorLayout = buildDot(false)
        dotIndicatorView = dotIndicatorLayout!!.findViewById(R.id.dot)
        addView(dotIndicatorLayout)
        dotIndicatorXSpring = SpringAnimation(dotIndicatorLayout, SpringAnimation.TRANSLATION_X)
        val springForceX = SpringForce(0F)
        springForceX.dampingRatio = 1f
        springForceX.stiffness = 300F
        dotIndicatorXSpring!!.spring = springForceX

        val floatPropertyCompat = object: FloatPropertyCompat<View>("DotsWidth") {
            override fun setValue(`object`: View?, value: Float) {
                val params = dotIndicatorView!!.layoutParams
                params.width = value.toInt()
                dotIndicatorView!!.requestLayout()
            }

            override fun getValue(`object`: View?): Float {
                return dotIndicatorView!!.layoutParams.width.toFloat()
            }
        }

        dotIndicatorWidthSpring = SpringAnimation(dotIndicatorLayout, floatPropertyCompat)

        val springForceWidth = SpringForce(0F)
        springForceWidth.dampingRatio = 1f
        springForceWidth.stiffness = 300F
        dotIndicatorWidthSpring!!.spring = springForceWidth
    }

    private fun addStrokeDots(count: Int) {
        for (i in 0 until count) {
            val dot = buildDot(true)
            dot.setOnClickListener {
                if (dotsClickable && viewPager != null && viewPager!!.adapter != null && i < viewPager!!.adapter!!.count) {
                    viewPager!!.setCurrentItem(i, true)
                }
            }

            strokeDots!!.add(dot.findViewById(R.id.dot) as ImageView)
            strokeDotsLinearLayout.addView(dot)
        }
    }

    private fun buildDot(stroke: Boolean): ViewGroup {
        val dot = LayoutInflater.from(context).inflate(R.layout.layout_dot, this, false) as ViewGroup
        val dotImageView = dot.findViewById<ImageView>(R.id.dot)
        dotImageView.background = ContextCompat.getDrawable(
            context,
            if (stroke) R.drawable.dot_stroke_background else R.drawable.dot_background
        )

        val params = dotImageView.layoutParams as RelativeLayout.LayoutParams
        params.height = dotsSize
        params.width = params.height
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

        params.setMargins(dotsSpacing, 0, dotsSpacing, 0)

        setUpDotBackground(stroke, dotImageView)
        return dot
    }

    private fun setUpDotBackground(stroke: Boolean, dotImageView: View) {
        val dotBackground = dotImageView.background as GradientDrawable
        if (stroke) {
            dotBackground.setStroke(dotsStrokeWidth, dotsStrokeColor)
        } else {
            dotBackground.setColor(dotIndicatorColor)
        }
        dotBackground.cornerRadius = dotsCornerRadius.toFloat()
    }

    private fun removeDots(count: Int) {
        for (i in 0 until count) {
            strokeDotsLinearLayout.removeViewAt(strokeDotsLinearLayout.childCount - 1)
            strokeDots!!.removeAt(strokeDots.size - 1)
        }
    }

    private fun setUpDotsAnimators() {
        if (viewPager != null && viewPager!!.adapter != null && viewPager!!.adapter!!.count > 0) {
            if (pageChangedListener != null) {
                viewPager!!.removeOnPageChangeListener(pageChangedListener!!)
            }
            setUpOnPageChangedListener()
            viewPager!!.addOnPageChangeListener(pageChangedListener!!)
            pageChangedListener!!.onPageScrolled(0, 0F, 0)
        }
    }

    private fun setUpOnPageChangedListener() {
        pageChangedListener = object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val stepX = dotsSize + dotsSpacing * 2
                val xFinalPosition: Float
                val widthFinalPosition: Float

                if (positionOffset >= 0 && positionOffset < 0.1f) {
                    xFinalPosition = (horizontalMargin + position * stepX).toFloat()
                    widthFinalPosition = dotsSize.toFloat()
                } else if (positionOffset in 0.1f..0.9f) {
                    xFinalPosition = (horizontalMargin + position * stepX).toFloat()
                    widthFinalPosition = (dotsSize + stepX).toFloat()
                } else {
                    xFinalPosition = (horizontalMargin + (position + 1) * stepX).toFloat()
                    widthFinalPosition = dotsSize.toFloat()
                }

                if (dotIndicatorXSpring!!.spring.finalPosition != xFinalPosition) {
                    dotIndicatorXSpring!!.spring.finalPosition = xFinalPosition
                }

                if (dotIndicatorWidthSpring!!.spring.finalPosition != widthFinalPosition) {
                    dotIndicatorWidthSpring!!.spring.finalPosition = widthFinalPosition
                }

                if (!dotIndicatorXSpring!!.isRunning) {
                    dotIndicatorXSpring!!.start()
                }

                if (!dotIndicatorWidthSpring!!.isRunning) {
                    dotIndicatorWidthSpring!!.start()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

        }
    }

    private fun setUpViewPager() {
        if (viewPager!!.adapter != null) {
            viewPager!!.adapter!!.registerDataSetObserver(object : DataSetObserver() {
                override fun onChanged() {
                    super.onChanged()
                    refreshDots()
                }
            })
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (context.resources.displayMetrics.density * dp).toInt()
    }

    fun getThemePrimaryColor(context: Context): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(R.attr.colorPrimary, value, true)
        return value.data
    }

    fun setDotIndicatorColor(color: Int) {
        if (dotIndicatorView != null) {
            dotIndicatorColor = color
            setUpDotBackground(false, dotIndicatorView!!)
        }
    }

    fun setStrokeDotsIndicatorColor(color: Int) {
        if (strokeDots != null && !strokeDots.isEmpty()) {
            dotsStrokeColor = color
            for (v in strokeDots) {
                setUpDotBackground(true, v)
            }
        }
    }

    fun setDotsClickable(dotsClickable: Boolean) {
        this.dotsClickable = dotsClickable
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        setUpViewPager()
        refreshDots()
    }
}

