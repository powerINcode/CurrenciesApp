package com.powerman23rus.currenciesapp.di.modules.builders

import com.powerman23rus.currenciesapp.di.modules.ui.screens.MainActivityModule
import com.powerman23rus.currenciesapp.ui.screens.info.InfoActivity
import com.powerman23rus.currenciesapp.ui.screens.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by powerman23rus on 10/04/2019.
 */

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun buildMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun buildInfoActivity() : InfoActivity
}