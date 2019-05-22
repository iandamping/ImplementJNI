package com.ian.app

import android.app.Application
import com.google.gson.Gson
import com.ian.app.api.ApiInterface
import com.ian.app.helper.NetworkConfig
import com.ian.app.helper.SecretKeyHelper.apiKey
import com.ian.app.helper.SecretKeyHelper.baseUrl
import com.ian.app.helper.SecretKeyHelper.communityId
import com.ian.app.helper.SecretKeyHelper.encriptionKey


/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
class ImplementApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DataConfig.setBaseUrl(baseUrl)
        DataConfig.setApiKey(apiKey)
        DataConfig.setEncriptionKey(encriptionKey)
        DataConfig.setCommunityID(communityId)
    }
}