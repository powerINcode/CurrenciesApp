package com.powerman23rus.currenciesapp.core.extensions.common

import java.util.*

/**
 * Created by powerman23rus on 11/04/2019.
 */
fun Double.format(digits : Int) = String.format(Locale.ROOT, "%.${digits}f", this)