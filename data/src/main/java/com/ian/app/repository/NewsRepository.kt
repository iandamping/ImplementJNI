package com.ian.app.repository

import android.content.Context
import com.google.gson.Gson
import com.ian.app.DataConfig
import com.ian.app.DataConfig.getCommunityID
import com.ian.app.DataConfig.getEncriptionKey
import com.ian.app.DataConfig.getRestClient
import com.ian.app.api.ApiInterface
import com.ian.app.data.R
import com.ian.app.helper.*
import com.ian.app.helper.DataConstant.deviceID
import com.ian.app.helper.DataConstant.parseFailed
import com.ian.app.model.GeneralData

/**
 *
Created by Ian Damping on 22/05/2019.
Github = https://github.com/iandamping
 */
 class NewsRepository {
    private val gson = Gson()

    fun getNews(successGetData:(GeneralData?) -> Unit, failedGetData:()->Unit) {
        getRestClient().getAllNews(deviceID, getCommunityID(), encryptedData()).executes({
            logE(it.localizedMessage)
        }, {
            it?.let { data ->
                when {
                    data.status == "200" -> {
                        val tempGetData: String = gson.fromJson(gson.toJson(FTAes.decrypt(data.data, getEncriptionKey())), String::class.java)
                        val dataParsed: GeneralData = gson.fromJson(tempGetData, GeneralData::class.java)
                        successGetData(dataParsed)

                    }
                    else -> {
                        logE(data.data + parseFailed)
                        failedGetData()
                    }
                }


            }
        })
    }

    private fun encryptedData(): String {
        val jsonData = "{\n" +
                " \"page\": \"" + "1" + "\",\n" +
                " \"page_size\": \"" + "10" + "\",\n" +
                " \"device_id\": \"" + deviceID + "\",\n" +
                " \"community_id\": \"" + getCommunityID() + "\",\n" +
                " \"type\": \"" + "news" + "\",\n" +
                " \"email\": \"" + "" + "\",\n" +
                " \"keyword\": \"" + "" + "\"\n" +
                "}"
        return FTAes.encrypt(jsonData, getEncriptionKey())
    }
}