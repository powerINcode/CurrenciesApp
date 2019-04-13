package com.powerman23rus.currenciesapp.di.modules.data.repositories

import com.powerman23rus.currenciesapp.data.repositories.currencies.CurrencyRepositoryImpl
import com.powerman23rus.currenciesapp.domain.repositories.CurrencyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by powerman23rus on 12/04/2019.
 */
@Module
abstract class RepositoriesModule {
    @Singleton
    @Binds
    abstract fun bindCurrencyRepository(repository : CurrencyRepositoryImpl) : CurrencyRepository
}