package com.powerman23rus.currenciesapp.data.repositories.currencies

import com.powerman23rus.currenciesapp.R
import com.powerman23rus.currenciesapp.data.api.currencies.enums.Currencies
import com.powerman23rus.currenciesapp.data.api.currencies.response.CurrenciesModel
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import javax.inject.Inject

/**
 * Created by powerman23rus on 12/04/2019.
 */
class CurrencyMapper @Inject constructor() {
    fun mapCurrenciesRates(model : CurrenciesModel) : List<Rate> {
        val currenciesRate = model.rates
        val rates = mutableListOf<Rate>()
        rates.add(
            Rate(
                Currencies.AUD.base,
                currenciesRate.AUD,
                R.string.currency_AUD,
                R.drawable.ic_australia
            )
        )
        rates.add(
            Rate(
                Currencies.BGN.base,
                currenciesRate.BGN,
                R.string.currency_BGN,
                R.drawable.ic_bulgaria
            )
        )
        rates.add(
            Rate(
                Currencies.BRL.base,
                currenciesRate.BRL,
                R.string.currency_BRL,
                R.drawable.ic_brazil
            )
        )
        rates.add(
            Rate(
                Currencies.CAD.base,
                currenciesRate.CAD,
                R.string.currency_CAD,
                R.drawable.ic_canada
            )
        )
        rates.add(
            Rate(
                Currencies.CHF.base,
                currenciesRate.CHF,
                R.string.currency_CHF,
                R.drawable.ic_sweden
            )
        )
        rates.add(
            Rate(
                Currencies.CNY.base,
                currenciesRate.CNY,
                R.string.currency_CNY,
                R.drawable.ic_china
            )
        )
        rates.add(
            Rate(
                Currencies.CZK.base,
                currenciesRate.CZK,
                R.string.currency_CZK,
                R.drawable.ic_czech
            )
        )
        rates.add(
            Rate(
                Currencies.DKK.base,
                currenciesRate.DKK,
                R.string.currency_DKK,
                R.drawable.ic_denmark
            )
        )
        rates.add(
            Rate(
                Currencies.EUR.base,
                currenciesRate.EUR,
                R.string.currency_EUR,
                R.drawable.ic_european_union
            )
        )
        rates.add(
            Rate(
                Currencies.GBP.base,
                currenciesRate.GBP,
                R.string.currency_GBP,
                R.drawable.ic_united_kingdom
            )
        )
        rates.add(
            Rate(
                Currencies.HKD.base,
                currenciesRate.HKD,
                R.string.currency_HKD,
                R.drawable.ic_hong_kong
            )
        )
        rates.add(
            Rate(
                Currencies.HRK.base,
                currenciesRate.HRK,
                R.string.currency_HRK,
                R.drawable.ic_croatia
            )
        )
        rates.add(
            Rate(
                Currencies.HUF.base,
                currenciesRate.HUF,
                R.string.currency_HUF,
                R.drawable.ic_hungary
            )
        )
        rates.add(
            Rate(
                Currencies.IDR.base,
                currenciesRate.IDR,
                R.string.currency_IDR,
                R.drawable.ic_indonesia
            )
        )
        rates.add(
            Rate(
                Currencies.ILS.base,
                currenciesRate.ILS,
                R.string.currency_ILS,
                R.drawable.ic_israel
            )
        )
        rates.add(
            Rate(
                Currencies.INR.base,
                currenciesRate.INR,
                R.string.currency_INR,
                R.drawable.ic_india
            )
        )
        rates.add(
            Rate(
                Currencies.ISK.base,
                currenciesRate.ISK,
                R.string.currency_ISK,
                R.drawable.ic_iceland
            )
        )
        rates.add(
            Rate(
                Currencies.JPY.base,
                currenciesRate.JPY,
                R.string.currency_JPY,
                R.drawable.ic_japan
            )
        )
        rates.add(
            Rate(
                Currencies.KRW.base,
                currenciesRate.KRW,
                R.string.currency_KRW,
                R.drawable.ic_south_korea
            )
        )
        rates.add(
            Rate(
                Currencies.MXN.base,
                currenciesRate.MXN,
                R.string.currency_MXN,
                R.drawable.ic_mexico
            )
        )
        rates.add(
            Rate(
                Currencies.MYR.base,
                currenciesRate.MYR,
                R.string.currency_MYR,
                R.drawable.ic_malaysia
            )
        )
        rates.add(
            Rate(
                Currencies.NOK.base,
                currenciesRate.NOK,
                R.string.currency_NOK,
                R.drawable.ic_norway
            )
        )
        rates.add(
            Rate(
                Currencies.NZD.base,
                currenciesRate.NZD,
                R.string.currency_NZD,
                R.drawable.ic_new_zealand
            )
        )
        rates.add(
            Rate(
                Currencies.PHP.base,
                currenciesRate.PHP,
                R.string.currency_PHP,
                R.drawable.ic_philippines
            )
        )
        rates.add(
            Rate(
                Currencies.PLN.base,
                currenciesRate.PLN,
                R.string.currency_PLN,
                R.drawable.ic_poland
            )
        )
        rates.add(
            Rate(
                Currencies.RON.base,
                currenciesRate.RON,
                R.string.currency_RON,
                R.drawable.ic_romania
            )
        )
        rates.add(
            Rate(
                Currencies.SEK.base,
                currenciesRate.SEK,
                R.string.currency_SEK,
                R.drawable.ic_switzerland
            )
        )
        rates.add(
            Rate(
                Currencies.SGD.base,
                currenciesRate.SGD,
                R.string.currency_SGD,
                R.drawable.ic_singapore
            )
        )
        rates.add(
            Rate(
                Currencies.THB.base,
                currenciesRate.THB,
                R.string.currency_THB,
                R.drawable.ic_thailand
            )
        )
        rates.add(
            Rate(
                Currencies.TRY.base,
                currenciesRate.TRY,
                R.string.currency_TRY,
                R.drawable.ic_turkey
            )
        )
        rates.add(
            Rate(
                Currencies.USD.base,
                currenciesRate.USD,
                R.string.currency_USD,
                R.drawable.ic_united_states
            )
        )
        rates.add(
            Rate(
                Currencies.ZAR.base,
                currenciesRate.ZAR,
                R.string.currency_ZAR,
                R.drawable.ic_south_africa
            )
        )

        return rates
    }
}