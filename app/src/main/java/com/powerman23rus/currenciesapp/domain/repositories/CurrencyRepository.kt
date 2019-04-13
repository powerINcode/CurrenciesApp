package com.powerman23rus.currenciesapp.domain.repositories

import com.powerman23rus.currenciesapp.data.api.currencies.enums.Currencies
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate

/**
 * Created by powerman23rus on 12/04/2019.
 */
interface CurrencyRepository {
    suspend fun getCurrencies(base : Currencies) : List<Rate>
}