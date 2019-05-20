package com.ian.app.helper

import android.util.Log

/**
 *
Created by Ian Damping on 30/04/2019.
Github = https://github.com/iandamping
 */

inline fun <reified T> T.logE(msg: String?) {
    val tag = T::class.java.name
    Log.e(tag, msg)
}
