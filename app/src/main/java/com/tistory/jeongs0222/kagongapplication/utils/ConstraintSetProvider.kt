package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager


interface ConstraintSetProvider {
    fun moreExpandAnimation(layout: Int, sceneRoot: ConstraintLayout)

    fun moreContractAnimation(layout: Int, sceneRoot: ConstraintLayout)
}

class ConstraintSetProviderImpl(
    private val context: Context
): ConstraintSetProvider {
    override fun moreExpandAnimation(layout: Int, sceneRoot: ConstraintLayout) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, layout)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 700

        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        constraintSet.applyTo(sceneRoot)
    }

    override fun moreContractAnimation(layout: Int, sceneRoot: ConstraintLayout) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(context, layout)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 700

        TransitionManager.beginDelayedTransition(sceneRoot, transition)

        constraintSet.applyTo(sceneRoot)
    }

}