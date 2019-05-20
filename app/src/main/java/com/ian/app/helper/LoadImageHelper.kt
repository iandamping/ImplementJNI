package com.ian.app.helper

import android.widget.ImageView
import com.ian.app.R
import com.squareup.picasso.Picasso

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

fun ImageView.loadUrl(url: String?) {
    url?.let { Picasso.get().load(it).placeholder(R.drawable.empty_image).into(this) }

}