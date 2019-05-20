package com.ian.app

import android.app.Application
import com.google.gson.Gson
import com.ian.app.api.ApiInterface
import com.ian.app.helper.NetworkConfig


/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
class ImplementApp : Application() {
    companion object {
        lateinit var gson: Gson
        lateinit var api: ApiInterface
    }

    override fun onCreate() {
        super.onCreate()
        gson = Gson()
        api = NetworkConfig.getRetrofit(this).create(ApiInterface::class.java)
    }
}