package com.powerman23rus.currenciesapp.core.livedata

import android.arch.lifecycle.LiveData

/**
 * Created by powerman23rus on 10/04/2019.
 */
open class LiveEvent<T> : LiveData<Event<T>>()