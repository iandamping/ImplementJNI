package com.ian.app

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

    fun getBaseUrl():String = baseUrl
    fun getApiKey():String = apiKey
    fun getEncriptionKey():String = encriptionKey
    fun getCommunityID():String = communityId
}