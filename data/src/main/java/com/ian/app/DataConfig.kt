package com.ian.app

import android.content.Context
import com.ian.app.api.ApiInterface
import com.ian.app.helper.NetworkConfig

/**
 *
Created by Ian Damping on 22/05/2019.
Github = https://github.com/iandamping
 */
object DataConfig{
    private lateinit var baseUrl: String
    private lateinit var apiKey: String
    private  lateinit var encriptionKey: String
    private lateinit var communityId: String
    private lateinit var api:ApiInterface

    fun setBaseUrl(base:String){
        this.baseUrl = base
    }
    fun setApiKey(apiKey:String){
        this.apiKey = apiKey
    }
    fun setEncriptionKey(encriptKey:String){
        this.encriptionKey = encriptKey
    }
    fun setCommunityID(communityID:String){
        this.communityId = communityID
    }

    fun setRestClient(ctx:Context){
        this.api = NetworkConfig.getRetrofit(ctx).create(ApiInterface::class.java)
    }

    fun getBaseUrl():String = baseUrl
    fun getApiKey():String = apiKey
    fun getEncriptionKey():String = encriptionKey
    fun getCommunityID():String = communityId
    fun getRestClient():ApiInterface = api
}