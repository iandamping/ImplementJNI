package com.ian.app.helper

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity


/**
 *
Created by Ian Damping on 15/04/2019.
Github = https://github.com/iandamping
 */
fun FragmentActivity.fullScreenAnimation() {
    this.overridePendingTransition(com.ian.app.R.anim.fade_in_activity, com.ian.app.R.anim.fade_out_activity)
    this.requestWindowFeature(Window.FEATURE_NO_TITLE)
    this.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

