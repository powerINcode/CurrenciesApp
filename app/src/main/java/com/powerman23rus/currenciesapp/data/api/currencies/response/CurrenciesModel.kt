package com.powerman23rus.currenciesapp.data.api.currencies.response

import java.util.*

data class CurrenciesModel(
    val base : String,
    val date : Date,
    val rates : CurrenciesRatesModel
)