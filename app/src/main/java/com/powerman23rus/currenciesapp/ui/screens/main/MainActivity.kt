package com.powerman23rus.currenciesapp.ui.screens.main

import android.os.Bundle
import android.support.v7.widget.SimpleItemAnimator
import android.view.Menu
import android.view.MenuItem
import com.powerman23rus.currenciesapp.R
import com.powerman23rus.currenciesapp.core.extensions.common.exhaustive
import com.powerman23rus.currenciesapp.core.extensions.views.hideKeyboard
import com.powerman23rus.currenciesapp.core.ui.animations.fadeIn
import com.powerman23rus.currenciesapp.core.ui.animations.fadeOut
import com.powerman23rus.currenciesapp.core.ui.screens.BaseViewModelActivity
import com.powerman23rus.currenciesapp.core.ui.screens.recyclerview.BaseRecyclerDecorator
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import com.powerman23rus.currenciesapp.ui.screens.info.InfoActivity
import com.powerman23rus.currenciesapp.ui.screens.main.adapter.RatesRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class MainActivity : BaseViewModelActivity<MainViewModel>(),
    RatesRecyclerViewAdapter.RatesRecyclerViewAdapterEvent {

    private val ratesAdapter : RatesRecyclerViewAdapter = RatesRecyclerViewAdapter()

    override fun getViewModelClass() : Class<MainViewModel> = MainViewModel::class.java
    override fun getLayoutId() : Int = R.layout.activity_main

    override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item : MenuItem?) : Boolean {
        return if (item?.itemId == R.id.action_info) {
            viewModel.onInfoClick()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        ratesAdapter.listener = this
        btn_main_refresh.setOnClickListener { viewModel.onRefreshClick() }
        with(rv_main) {
            setHasFixedSize(true)
            adapter = ratesAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            addItemDecoration(BaseRecyclerDecorator(0, 16, 24, 16))
            setOnTouchListener { v, _ ->
                v.hideKeyboard()
                return@setOnTouchListener false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        ratesAdapter.listener = null
    }

    override fun onObserveViewModel(vm : MainViewModel) {
        super.onObserveViewModel(vm)

        with(vm) {
            state.observe(::onStateChange)
            navigation.observeEvent(::onNavigation)
            rates.observe(::onRatesSet)
            ratesSwap.observeEvent(::onRatesSwap)
            updateRates.observeEvent(::onUpdateRates)
        }
    }

    //region RatesRecyclerViewAdapter.RatesRecyclerViewAdapterEvent
    override fun onRateClick(rate : Rate) {
        viewModel.onRateClick(rate)
    }

    override fun onRateChange(rate : Rate, value : Double) {
        viewModel.onRateChange(rate, value)
    }
    //endregion

    private fun onStateChange(state : MainViewModel.MainViewState) {
        when (state) {
            MainViewModel.MainViewState.ClearState -> runBlocking {
                tv_main_connection_error.fadeOut()
                delay(ERROR_ANIMATION_DELAY)
                btn_main_refresh.fadeOut()
            }
            MainViewModel.MainViewState.ErrorState -> runBlocking {
                tv_main_connection_error.fadeIn()
                delay(ERROR_ANIMATION_DELAY)
                btn_main_refresh.fadeIn()
            }
        }.exhaustive
    }

    private fun onNavigation(navigation : MainViewModel.MainNavigationState) {
        when(navigation) {
            MainViewModel.MainNavigationState.NavigateToInfo -> startActivity(InfoActivity.getIntent(this))
        }.exhaustive
    }

    private fun onRatesSet(rates : List<Rate>) {
        ratesAdapter.initData(rates)
    }

    private fun onRatesSwap(sapPosition : Int) {
        rv_main.post {
            rv_main.scrollToPosition(0)
            ratesAdapter.swapToTop(sapPosition)
        }
    }

    private fun onUpdateRates(rates : List<Rate>) {
        rv_main.post { ratesAdapter.updateRates(rates) }
    }

    companion object {
        const val ERROR_ANIMATION_DELAY : Long = 100
    }
}
