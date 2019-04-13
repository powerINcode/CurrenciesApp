package com.powerman23rus.currenciesapp.core.livedata.observers

import android.arch.lifecycle.Observer
import com.powerman23rus.currenciesapp.core.livedata.Event

/**
 * Created by powerman23rus on 10/04/2019.
 */
class EventObserver<T>(private val block : (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event : Event<T>?) {
        event?.getDataIfNotHandled()?.let { block(it) }
    }
}