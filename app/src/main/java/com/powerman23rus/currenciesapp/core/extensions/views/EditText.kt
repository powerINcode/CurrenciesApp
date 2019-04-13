package com.powerman23rus.currenciesapp.core.extensions.views

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


/**
 * Created by powerman23rus on 11/04/2019.
 */
var EditText.textIfDifferent : String?
    get() = this.text?.toString()
    set(value) {
        if (value != this.text?.toString()) {
            this.setText(value)
        }
    }

fun EditText.doAfterTextChange(block : (String?) -> Unit) : TextWatcher {
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s : Editable?) {
            block.invoke(s?.toString())
        }

        override fun beforeTextChanged(s : CharSequence?, start : Int, count : Int, after : Int) {

        }

        override fun onTextChanged(s : CharSequence?, start : Int, before : Int, count : Int) {

        }

    }
    addTextChangedListener(textWatcher)

    return textWatcher
}