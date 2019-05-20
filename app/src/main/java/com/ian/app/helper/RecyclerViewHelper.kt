package com.ian.app.helper

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ian.app.base.MyKotlinAdapter

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

fun <T> RecyclerView.setUp(
    items: List<T>,
    layoutResId: Int,
    bindHolder: View.(T) -> Unit,
    itemClick: T.() -> Unit = {},
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): MyKotlinAdapter<T> {

    return MyKotlinAdapter(items, layoutResId, { bindHolder(it) }, {
        itemClick()
    }).apply {
        layoutManager = manager
        adapter = this
    }
}

