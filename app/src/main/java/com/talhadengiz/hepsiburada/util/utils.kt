package com.talhadengiz.hepsiburada.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.talhadengiz.hepsiburada.R

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions().placeholder(progressDrawable).error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

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