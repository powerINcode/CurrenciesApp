package com.powerman23rus.currenciesapp.di.modules.ui.screens

import android.arch.lifecycle.ViewModel
import com.powerman23rus.currenciesapp.di.keys.ViewModelKey
import com.powerman23rus.currenciesapp.ui.screens.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by powerman23rus on 10/04/2019.
 */
@Module
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainModule(viewModel : MainViewModel) : ViewModel
}