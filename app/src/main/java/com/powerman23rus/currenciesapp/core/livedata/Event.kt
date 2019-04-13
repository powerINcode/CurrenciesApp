package com.powerman23rus.currenciesapp.core.livedata

/**
 * Created by powerman23rus on 10/04/2019.
 */
class Event<T>(val data : T?) {
    var isHandled : Boolean = false
        private set

    fun getDataIfNotHandled() : T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            data
        }
    }

}