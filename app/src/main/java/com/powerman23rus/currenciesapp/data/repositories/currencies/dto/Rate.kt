package com.powerman23rus.currenciesapp.data.repositories.currencies.dto

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

data class Rate(
    val base : String,
    var value : Double,
    @StringRes val description : Int,
    @DrawableRes val icon : Int)