package com.powerman23rus.currenciesapp.di.modules.data.api

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.powerman23rus.currenciesapp.BuildConfig.BASE_URL
import com.powerman23rus.currenciesapp.core.exceptions.NoConnectionException
import com.powerman23rus.currenciesapp.data.api.currencies.CurrenciesService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton


/**
 * Created by powerman23rus on 10/04/2019.
 */
@Module
object NetworkModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideCurrenciesService(retrofit : Retrofit): CurrenciesService {
        return retrofit.create(CurrenciesService::class.java)
    }

    @Singleton
    @JvmStatic
    @Provides
    fun provideRetrofit(converterFactory: GsonConverterFactory, httpClient : OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return GsonConverterFactory.create(gson)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {

                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    if (!isOnline()) {
                        throw NoConnectionException()
                    }
                    val request = chain.request()

                    return chain.proceed(request)
                }

                private fun isOnline(): Boolean {
                    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val netInfo = connectivityManager.activeNetworkInfo
                    return netInfo != null && netInfo.isConnected
                }
            })
            .build()
    }
}