package com.powerman23rus.currenciesapp.core.extensions.common

import android.content.res.Resources

/**
 * Created by powerman23rus on 10/04/2019.
 */

fun Int.toDp() : Float {
    return this * Resources.getSystem().displayMetrics.density
}