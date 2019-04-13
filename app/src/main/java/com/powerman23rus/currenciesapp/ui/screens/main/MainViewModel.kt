package com.powerman23rus.currenciesapp.ui.screens.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.powerman23rus.currenciesapp.core.livedata.LiveEvent
import com.powerman23rus.currenciesapp.core.livedata.MutableLiveEvent
import com.powerman23rus.currenciesapp.core.ui.screens.BaseViewModel
import com.powerman23rus.currenciesapp.data.api.currencies.enums.Currencies
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import com.powerman23rus.currenciesapp.domain.usecases.CalculateRatesInteractor
import com.powerman23rus.currenciesapp.domain.usecases.GetCurrencyInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Created by powerman23rus on 10/04/2019.
 */
class MainViewModel @Inject constructor(
    private val getCurrency : GetCurrencyInteractor,
    private val calculateRates : CalculateRatesInteractor
) :
    BaseViewModel() {

    private val _rates : MutableLiveData<List<Rate>> = MutableLiveData()
    val rates : LiveData<List<Rate>> = _rates

    private val _ratesSwap : MutableLiveEvent<Int> = MutableLiveEvent()
    val ratesSwap : LiveEvent<Int> = _ratesSwap

    private val _updateRates : MutableLiveEvent<List<Rate>> = MutableLiveEvent()
    val updateRates : LiveEvent<List<Rate>> = _updateRates

    private val _state : MutableLiveData<MainViewState> = MutableLiveData()
    val state : LiveData<MainViewState> = _state

    private val _navigation : MutableLiveEvent<MainNavigationState> = MutableLiveEvent()
    val navigation : LiveEvent<MainNavigationState> = _navigation

    private var _ratioRates : List<Rate>? = null
    private var _activeRate : Rate? = null
    private var _refreshJob : Job? = null
    private var _isRefreshActive : Boolean = false
        set(value) {
            field = value
            if (!value) {
                _refreshJob?.cancel()
            }
        }

    init {
        _state.value = MainViewState.ClearState
        loadCurrencies()
    }

    override fun onNetworkProblemOccur() {
        super.onNetworkProblemOccur()
        _isRefreshActive = false
        _state.value = MainViewState.ErrorState
    }

    fun onResume() {
        if (_activeRate != null) {
            startRatesWatching()
        }
    }

    fun onPause() {
        _isRefreshActive = false
    }

    fun onRefreshClick() {
        _state.value = MainViewState.ClearState
        if (_activeRate == null) {
            loadCurrencies()
        } else {
            startRatesWatching()
        }
    }

    fun onRateClick(rate : Rate) {
        val rates = _rates.value ?: return
        if (_activeRate == rate) {
            return
        }

        _activeRate = rate
        setOrSwapRates(rate, rates, true)
    }

    fun onRateChange(rate : Rate, value : Double) {
        val rates = _rates.value ?: return
        val sourceRate = rates.firstOrNull { it.base == rate.base }
        if (sourceRate != null && sourceRate.value != value) {
            sourceRate.value = value
            recalculateRates(sourceRate, rates)
        }
    }

    fun onInfoClick() {
        _navigation.event = MainNavigationState.NavigateToInfo
    }

    private fun loadCurrencies() {
        request {
            _ratioRates = getCurrency(MAIN_RATE)
            var rates = _ratioRates ?: return@request
            if (rates.isNotEmpty()) {
                val mainRate = rates.firstOrNull { it.base == MAIN_RATE.base }
                val rate = if (mainRate != null) {
                    mainRate.value = 1.0
                    rates = getArrangedRates(mainRate, rates)
                    mainRate
                } else {
                    rates.first()
                }

                _activeRate = rate
                setOrSwapRates(rate, rates, false)

                delay(REFRESH_DELAY_MILLISECONDS)
                startRatesWatching()
            }
        }
    }

    private fun startRatesWatching() {
        if (!_isRefreshActive) {
            _isRefreshActive = true
            _refreshJob = safeLaunch {
                while (_isRefreshActive) {
                    _ratioRates = getCurrency(MAIN_RATE)
                    refreshRates()
                    delay(REFRESH_DELAY_MILLISECONDS)
                }
            }
        }
    }

    private fun refreshRates() {
        val active = _activeRate ?: return
        val currentRates = _rates.value ?: return
        recalculateRates(active, currentRates)
    }

    private fun setOrSwapRates(active : Rate, rates : List<Rate>, isSwap : Boolean) {
        var result = rates
        if (isSwap) {
            val indexOfActiveRate = rates.indexOfFirst { it.base == active.base }
            _ratesSwap.event = indexOfActiveRate
            result = getArrangedRates(active, rates)
        }

        recalculateRates(active, result)
    }

    private fun recalculateRates(active : Rate, rates : List<Rate>) {
        val ratioRates = _ratioRates ?: return
        calculateRates.block(CalculateRatesInteractor.Params(active, rates, ratioRates))

        _rates.value = rates
        _updateRates.event = rates
    }

    private fun getArrangedRates(active : Rate, rates : List<Rate>) : List<Rate> {
        val tmpRates = rates.toMutableList()
        val indexOfActiveRate = rates.indexOfFirst { it.base == active.base }
        val newActive = tmpRates.removeAt(indexOfActiveRate)
        tmpRates.add(0, newActive)
        return tmpRates
    }

    sealed class MainViewState {
        object ClearState : MainViewState()
        object ErrorState : MainViewState()
    }

    sealed class MainNavigationState {
        object NavigateToInfo : MainNavigationState()
    }

    companion object {
        @JvmField
        val MAIN_RATE : Currencies = Currencies.EUR

        @JvmStatic
        val REFRESH_DELAY_MILLISECONDS : Long = 1000
    }
}