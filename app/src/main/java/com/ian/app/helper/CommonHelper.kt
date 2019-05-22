package com.ian.app.helper

import android.util.Log
import android.widget.ImageView
import com.ian.app.R
import com.squareup.picasso.Picasso

/**
 *
Created by Ian Damping on 22/05/2019.
Github = https://github.com/iandamping
 */

inline fun <reified T> T.logE(msg: String?) {
    val tag = T::class.java.name
    Log.e(tag, msg)
}

fun ImageView.loadUrl(url: String?) {
    url?.let { Picasso.get().load(it).placeholder(R.drawable.empty_image).into(this) }

}