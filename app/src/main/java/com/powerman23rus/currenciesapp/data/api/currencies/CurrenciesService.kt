package com.powerman23rus.currenciesapp.data.api.currencies

import com.powerman23rus.currenciesapp.data.api.currencies.response.CurrenciesModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by powerman23rus on 10/04/2019.
 */
interface CurrenciesService {
    @GET("latest")
    fun getLatestCurrenciesAsync(@Query("base") base : String) : Deferred<CurrenciesModel>
}