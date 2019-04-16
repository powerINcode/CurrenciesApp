package com.powerman23rus.currenciesapp.ui.screens.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.powerman23rus.currenciesapp.core.extensions.common.format
import com.powerman23rus.currenciesapp.core.extensions.views.doAfterTextChange
import com.powerman23rus.currenciesapp.core.extensions.views.showKeyboard
import com.powerman23rus.currenciesapp.core.extensions.views.textIfDifferent
import com.powerman23rus.currenciesapp.core.ui.screens.recyclerview.BaseRecyclerViewAdapter
import com.powerman23rus.currenciesapp.data.repositories.currencies.dto.Rate
import kotlinx.android.synthetic.main.item_rate_list.view.*
import java.lang.NumberFormatException


/**
 * Created by powerman23rus on 10/04/2019.
 */
class RatesRecyclerViewAdapter : BaseRecyclerViewAdapter<Rate>() {
    var listener : RatesRecyclerViewAdapterEvent? = null

    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : RateViewHolder {
        return RateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.powerman23rus.currenciesapp.R.layout.item_rate_list,
                parent,
                false
            )
        )
    }

    override fun getItemId(position : Int) : Long {
        return data[position].base.hashCode().toLong()
    }

    fun initData(data : List<Rate>) {
        if (this.data.isEmpty()) {
            swapData(data.map { it.copy() })
        }
    }

    fun swapToTop(from : Int) {
        notifyItemMoved(from, 0)
    }

    fun updateRates(rates : List<Rate>) {
        data = rates
        notifyItemRangeChanged(1, rates.size - 1)
    }

    inner class RateViewHolder(view : View) : BaseViewHolder(view) {
        private val currencyIconImageView = view.iv_item_rate_currency_icon
        private val nameTextView = view.tv_item_rate_name
        private val currencyNameTextView = view.tv_item_rate_currency_name
        private val valueEditTextView = view.et_item_rate_value

        init {
            view.setOnClickListener {
                valueEditTextView.requestFocus()
                valueEditTextView.showKeyboard()
            }

            valueEditTextView.doAfterTextChange {
                val item = item
                if (adapterPosition == 0 && item != null) {
                    try {
                        val value = if (it.isNullOrEmpty()) 0.0 else it.replace(",", ".").toDouble()
                        listener?.onRateChange(item, value)
                    } catch (e : NumberFormatException) {

                    }
                }
            }
        }

        override fun bind(item : Rate) {
            super.bind(item)

            currencyIconImageView.setImageResource(item.icon)
            nameTextView.text = item.base
            currencyNameTextView.text = context.getString(item.description)
            valueEditTextView.textIfDifferent = item.value.format(2)

            valueEditTextView.onFocusChangeListener =
                View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        valueEditTextView.setSelection(valueEditTextView.text?.length ?: 0)
                        listener?.onRateClick(item)
                    }
                }
        }
    }

    interface RatesRecyclerViewAdapterEvent {
        fun onRateClick(rate : Rate)
        fun onRateChange(rate : Rate, value : Double)
    }
}