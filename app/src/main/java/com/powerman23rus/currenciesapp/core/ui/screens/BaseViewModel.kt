package com.powerman23rus.currenciesapp.core.ui.screens

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.powerman23rus.currenciesapp.core.exceptions.NoConnectionException
import com.powerman23rus.currenciesapp.core.livedata.LiveEvent
import com.powerman23rus.currenciesapp.core.livedata.MutableLiveEvent
import kotlinx.coroutines.*
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

/**
 * Created by powerman23rus on 10/04/2019.
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {
    protected var requestInProgress : Int = 0
    protected val _messageById : MutableLiveEvent<Int> = MutableLiveEvent()
    val messageId : LiveEvent<Int> = _messageById

    protected val _message : MutableLiveEvent<String> = MutableLiveEvent()
    val message : LiveEvent<String> = _message

    protected val _loader : MutableLiveData<Boolean> = MutableLiveData()
    val loader : LiveData<Boolean> = _loader

    protected val exceptionHandler : CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, error -> _message.event = error.message }

    private val job : Job = SupervisorJob()
    override val coroutineContext : CoroutineContext = Dispatchers.Main + job

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    protected fun showRequestProgress() {
        if (requestInProgress == 0) {
            _loader.value = true
        }

        requestInProgress++
    }

    protected fun hideRequestProgress() {
        requestInProgress--
        if (requestInProgress <= 0) {
            requestInProgress = 0
            _loader.value = false
        }
    }

    protected open fun onNetworkProblemOccur() {

    }

    protected fun request(block : suspend () -> Unit) {
        launch(exceptionHandler) {
            try {
                showRequestProgress()
                block()
            } catch (e : IOException) {
                processException(e)
            }
            catch (e : Exception) {
                if (e is CancellationException) throw e
                _message.event = e.message.toString()
            }
            finally {
                hideRequestProgress()
            }
        }
    }

    protected fun safeLaunch(block : suspend () -> Unit) : Job {
        return launch(exceptionHandler) {
            try {
                block()
            } catch (e : IOException) {
                processException(e)
            }
            catch (e : Exception) {
                if (e is CancellationException) throw e
                _message.event = e.message.toString()
            }
        }
    }

    protected open fun processException(e : IOException) {
        if (e is NoConnectionException || e is InterruptedIOException || e is ConnectException || e is UnknownHostException) {
            onNetworkProblemOccur()
        } else {
            throw e
        }
    }
}