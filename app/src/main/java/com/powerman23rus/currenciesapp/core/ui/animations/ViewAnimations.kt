package com.powerman23rus.currenciesapp.core.ui.animations

import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View

/**
 * Created by powerman23rus on 12/04/2019.
 */

const val SHORT_ANIMATION_DURATION : Long = 300

fun View.fadeIn() {
        animate()
        .setDuration(SHORT_ANIMATION_DURATION)
        .setInterpolator(FastOutSlowInInterpolator())
        .alpha(1f)
        .translationY(0f)
}

fun View.fadeOut() {
    animate()
        .setDuration(SHORT_ANIMATION_DURATION)
        .setInterpolator(FastOutSlowInInterpolator())
        .alpha(0f)
        .translationY(50f)
}