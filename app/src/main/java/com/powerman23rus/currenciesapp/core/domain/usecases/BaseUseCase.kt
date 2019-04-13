package com.powerman23rus.currenciesapp.core.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Created by powerman23rus on 12/04/2019.
 */
abstract class BaseUseCase<in Param, out Type> {

    protected abstract suspend fun run(param : Param) : Type

    suspend operator fun invoke(param : Param) : Type {
        return withContext(Dispatchers.IO) { run(param) }
    }

    fun block(param : Param) : Type = runBlocking {
        run(param)
    }
}