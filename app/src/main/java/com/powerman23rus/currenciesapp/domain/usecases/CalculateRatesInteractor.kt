package com.powerman23rus.currenciesapp.domain.usecases

import com.powerman23rus.currenciesapp.core.domain.usecases.BaseUseCase
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import com.powerman23rus.currenciesapp.ui.screens.main.MainViewModel
import javax.inject.Inject

/**
 * Created by powerman23rus on 12/04/2019.
 */
class CalculateRatesInteractor @Inject constructor() : BaseUseCase<CalculateRatesInteractor.Params, List<Rate>>() {
    override suspend fun run(param : Params) : List<Rate> {
        val active = param.activeRate
        val rates = param.currentRates
        val currenciesRates = param.ratioRates

        for (item in rates) {
            if (active.base == item.base) {
                continue
            } else {
                val currentRate = currenciesRates.firstOrNull { it.base == item.base } ?: return rates

                if (active.base == MainViewModel.MAIN_RATE.base) {
                    item.value = active.value * currentRate.value
                } else {
                    val activeRate = currenciesRates.first { it.base == active.base }

                    val eurValue = active.value / activeRate.value
                    if (currentRate.base == MainViewModel.MAIN_RATE.base) {
                        item.value = eurValue
                    } else {
                        item.value = eurValue * currentRate.value
                    }
                }
            }
        }

        return rates
    }

    class Params(val activeRate: Rate, val currentRates : List<Rate>, val ratioRates : List<Rate>)
}