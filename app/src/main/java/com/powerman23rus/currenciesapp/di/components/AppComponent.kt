package com.powerman23rus.currenciesapp.di.components

import com.powerman23rus.currenciesapp.App
import com.powerman23rus.currenciesapp.di.modules.AppModule
import com.powerman23rus.currenciesapp.di.modules.builders.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by powerman23rus on 10/04/2019.
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app : App) : Builder
        fun build() : AppComponent
    }

    fun inject(app : App)
}