package com.powerman23rus.currenciesapp.ui.screens.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.powerman23rus.currenciesapp.R
import com.powerman23rus.currenciesapp.core.ui.screens.BaseActivity
import kotlinx.android.synthetic.main.activity_info.*

/**
 * Created by powerman23rus on 12/04/2019.
 */
class InfoActivity : BaseActivity() {
    override fun getLayoutId() : Int = R.layout.activity_info

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tv_info_author_link.movementMethod = LinkMovementMethod.getInstance()
        tv_info_email_link.movementMethod = LinkMovementMethod.getInstance()
        tv_info_app_icon_link.movementMethod = LinkMovementMethod.getInstance()
        tv_info_icons_link.movementMethod = LinkMovementMethod.getInstance()
    }

    companion object {
        @JvmStatic
        fun getIntent(context : Context) : Intent = Intent(context, InfoActivity::class.java)
    }
}