package com.powerman23rus.currenciesapp.core.livedata.observers

import android.arch.lifecycle.Observer

/**
 * Created by powerman23rus on 10/04/2019.
 */
open class NotNullObserver<T>(protected val block : (T) -> Unit) : Observer<T> {
    override fun onChanged(data : T?) {
        data?.let { block(it) }
    }
}