package com.talhadengiz.searhmedia.util

import android.view.View
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

object AnimUtils {

    fun View.getAnimationToPositionX(finalPosition: Float): SpringAnimation {
        val animation = SpringAnimation(this, SpringAnimation.X)
        val spring = SpringForce()
        spring.finalPosition = finalPosition
        spring.stiffness = SpringForce.STIFFNESS_LOW
        spring.dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
        animation.spring = spring

        return animation
    }

}