package com.ian.app.api

import com.ian.app.helper.NetworkConfig.getAllNews
import com.ian.app.model.Base
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
interface ApiInterface {
    @FormUrlEncoded
    @POST(getAllNews)
    fun getAllNews(
        @Field("device_id") device_id: String?,
        @Field("community_id") community_id: String?,
        @Field("data") data: String?
    ): Call<Base<String>>
}