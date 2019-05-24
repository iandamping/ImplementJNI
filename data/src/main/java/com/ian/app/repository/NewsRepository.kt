package com.ian.app.repository

import android.content.Context
import com.google.gson.Gson
import com.ian.app.api.ApiInterface
import com.ian.app.data.helper.SecretKeyHelper.communityId
import com.ian.app.data.helper.SecretKeyHelper.encriptionKey
import com.ian.app.helper.DataConstant.deviceID
import com.ian.app.helper.DataConstant.parseFailed
import com.ian.app.helper.FTAes
import com.ian.app.helper.NetworkConfig
import com.ian.app.helper.executes
import com.ian.app.helper.logE
import com.ian.app.model.GeneralData

/**
 *
Created by Ian Damping on 22/05/2019.
Github = https://github.com/iandamping
 */
class NewsRepository(ctx: Context) {
    private val gson = Gson()
    private var api: ApiInterface = NetworkConfig.getRetrofit(ctx).create(ApiInterface::class.java)

    fun getNews(successGetData: (GeneralData?) -> Unit, failedGetData: () -> Unit) {
        api.getAllNews(deviceID, communityId, encryptedData()).executes({
            logE(it.localizedMessage)
        }, {
            it?.let { data ->
                when {
                    data.status == "200" -> {
                        val tempGetData: String =
                            gson.fromJson(gson.toJson(FTAes.decrypt(data.data, encriptionKey)), String::class.java)
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
                " \"community_id\": \"" + communityId + "\",\n" +
                " \"type\": \"" + "news" + "\",\n" +
                " \"email\": \"" + "" + "\",\n" +
                " \"keyword\": \"" + "" + "\"\n" +
                "}"
        return FTAes.encrypt(jsonData, encriptionKey)
    }
}