package com.powerman23rus.currenciesapp.core.extensions.views

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by powerman23rus on 10/04/2019.
 */

var View.isVisible : Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

fun View.showKeyboard() {
    val service = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    service.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    val service = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    service.hideSoftInputFromWindow(this.windowToken, 0)
}