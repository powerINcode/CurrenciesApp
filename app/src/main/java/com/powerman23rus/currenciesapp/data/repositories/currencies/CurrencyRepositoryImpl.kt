package com.powerman23rus.currenciesapp.data.repositories.currencies

import com.powerman23rus.currenciesapp.data.api.currencies.CurrenciesService
import com.powerman23rus.currenciesapp.data.api.currencies.enums.Currencies
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import com.powerman23rus.currenciesapp.domain.repositories.CurrencyRepository
import javax.inject.Inject

/**
 * Created by powerman23rus on 12/04/2019.
 */
class CurrencyRepositoryImpl @Inject constructor(
    private val currenciesService : CurrenciesService,
    private val mapper : CurrencyMapper
) :
    CurrencyRepository {
    override suspend fun getCurrencies(base : Currencies) : List<Rate> {
        return mapper.mapCurrenciesRates(currenciesService.getLatestCurrenciesAsync(base.base).await())
    }
}