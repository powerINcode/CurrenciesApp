package com.powerman23rus.currenciesapp.domain.usecases

import com.powerman23rus.currenciesapp.core.domain.usecases.BaseUseCase
import com.powerman23rus.currenciesapp.data.api.currencies.enums.Currencies
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import com.powerman23rus.currenciesapp.domain.repositories.CurrencyRepository
import javax.inject.Inject

/**
 * Created by powerman23rus on 12/04/2019.
 */
class GetCurrencyInteractor @Inject constructor(private val currencyRepository : CurrencyRepository) :
    BaseUseCase<Currencies, List<Rate>>() {
    override suspend fun run(param : Currencies) : List<Rate> {
        return currencyRepository.getCurrencies(param)
    }
}