package com.powerman23rus.currenciesapp.core.ui.screens

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.powerman23rus.currenciesapp.core.livedata.LiveEvent
import com.powerman23rus.currenciesapp.core.livedata.observers.EventObserver
import com.powerman23rus.currenciesapp.core.livedata.observers.NotNullObserver
import javax.inject.Inject

/**
 * Created by powerman23rus on 10/04/2019.
 */
abstract class BaseViewModelActivity<T : BaseViewModel> : BaseActivity() {
    @Inject
    protected lateinit var factory : ViewModelProvider.Factory
    protected lateinit var viewModel : T

    protected abstract fun getViewModelClass() : Class<T>

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory).get(getViewModelClass())
        onObserveViewModel(viewModel)
    }

    protected open fun onObserveViewModel(vm : T) {
        vm.messageId.observeEvent {
            toast(it)
        }

        vm.message.observeEvent {
            toast(it)
        }

        vm.loader.observe {
            showLoader(it)
        }
    }

    protected fun <T2> LiveData<T2>.observe(block : (T2) -> Unit) {
        observe(this@BaseViewModelActivity, NotNullObserver {
            block(it)
        })
    }

    protected fun <T2> LiveData<T2>.observeNullable(block : (T2?) -> Unit) {
        observe(this@BaseViewModelActivity, Observer {
            block(it)
        })
    }

    protected fun <T2> LiveEvent<T2>.observeEvent(block : (T2) -> Unit) {
        observe(this@BaseViewModelActivity, EventObserver {
            block(it)
        })
    }
}