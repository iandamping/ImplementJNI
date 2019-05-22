package com.ian.app.helper

import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ian.app.base.MyKotlinAdapter

/**
 *
Created by Ian Damping on 22/05/2019.
Github = https://github.com/iandamping
 */
fun ViewGroup.inflates(layout: Int): View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun <T> RecyclerView.setUp(items: List<T>, layoutResId: Int, bindHolder: View.(T) -> Unit, itemClick: T.() -> Unit = {}, manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)): MyKotlinAdapter<T> {

    return MyKotlinAdapter(items, layoutResId, { bindHolder(it) }, {
        itemClick()
    }).apply {
        layoutManager = manager
        adapter = this
    }
}

fun FragmentActivity.fullScreenAnimation() {
    this.overridePendingTransition(com.ian.app.R.anim.fade_in_activity, com.ian.app.R.anim.fade_out_activity)
    this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}
