package com.powerman23rus.currenciesapp.core.ui.screens.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by powerman23rus on 10/04/2019.
 */
abstract class BaseRecyclerViewAdapter<T> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter<T>.BaseViewHolder>() {

    protected var data : List<T> = emptyList()

    override fun getItemCount() : Int = data.size

    override fun onBindViewHolder(viewHolder : BaseViewHolder, position : Int) {
        viewHolder.bind(data[position])
    }

    open fun swapData(data : List<T>) {
        this.data = data
        notifyDataSetChanged()
    }

    abstract inner class BaseViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        protected var item : T? = null
        protected val context : Context get() = itemView.context

        open fun bind(item : T) {
            this.item = item
        }
    }

}