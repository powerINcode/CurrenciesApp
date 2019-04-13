package com.powerman23rus.currenciesapp.core.ui.screens.recyclerview

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.powerman23rus.currenciesapp.core.extensions.common.toDp

/**
 * Created by powerman23rus on 11/04/2019.
 */
class BaseRecyclerDecorator(
    val top : Int = 0,
    val end : Int = 0,
    val bottom : Int = 0,
    val start : Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect : Rect,
        view : View,
        parent : RecyclerView,
        state : RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = top.toDp().toInt()
        outRect.right = end.toDp().toInt()
        outRect.bottom = bottom.toDp().toInt()
        outRect.left = start.toDp().toInt()
    }
}