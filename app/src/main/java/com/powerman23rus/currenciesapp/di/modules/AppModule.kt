package com.powerman23rus.currenciesapp.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.powerman23rus.currenciesapp.App
import com.powerman23rus.currenciesapp.di.modules.data.api.NetworkModule
import com.powerman23rus.currenciesapp.di.modules.data.repositories.RepositoriesModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

/**
 * Created by powerman23rus on 10/04/2019.
 */

@Module(
    includes = [
        NetworkModule::class,
        RepositoriesModule::class
    ]
)
object AppModule {
    @JvmStatic
    @Provides
    fun provideAppContext(application : App) : Context = application

    @Suppress("UNCHECKED_CAST")
    @Provides
    @JvmStatic
    fun provideViewModelFactory(viewModels : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
                val viewModelProvider =
                    viewModels[modelClass]
                        ?: throw IllegalArgumentException("ViewModel class $modelClass not found")
                return viewModelProvider.get() as T
            }
        }

}