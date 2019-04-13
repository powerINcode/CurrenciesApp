package com.powerman23rus.currenciesapp.core.ui.screens

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.powerman23rus.currenciesapp.core.extensions.common.toDp
import com.powerman23rus.currenciesapp.core.extensions.views.isVisible
import dagger.android.AndroidInjection
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by powerman23rus on 12/04/2019.
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope {
    @LayoutRes
    protected abstract fun getLayoutId() : Int

    private var loadingView : View? = null

    private val job : Job = SupervisorJob()
    override val coroutineContext : CoroutineContext = Dispatchers.Main + job

    override fun onCreate(savedInstanceState : Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
        initLoader()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    protected open fun initLoader() {
        loadingView = ProgressBar(this).apply {
            layoutParams = FrameLayout.LayoutParams(36.toDp().toInt(), 36.toDp().toInt()).apply {
                gravity = Gravity.CENTER
            }

            isVisible = false
        }

        val container = findViewById<FrameLayout>(android.R.id.content)
        container.addView(loadingView)
    }

    protected fun showLoader(isShow : Boolean) {
        loadingView?.isVisible = isShow
        loadingView?.bringToFront()
    }

    protected fun toast(@StringRes messageId : Int) {
        toast(getString(messageId))
    }

    protected fun toast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}