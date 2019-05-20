package com.ian.app.ui.activity

import com.ian.app.ImplementApp.Companion.api
import com.ian.app.ImplementApp.Companion.gson
import com.ian.app.base.BasePresenter
import com.ian.app.helper.Constant.deviceId
import com.ian.app.helper.FTAes
import com.ian.app.helper.SecretKeyHelper.communityId
import com.ian.app.helper.SecretKeyHelper.encriptionKey
import com.ian.app.helper.executes
import com.ian.app.helper.logE
import com.ian.app.model.GeneralData

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
class MainPresenter : BasePresenter<MainView>() {
    override fun onCreate() {
        view()?.initView()
        fetchNewsData()
    }

    private fun fetchNewsData() {
        api.getAllNews(deviceId, communityId, encryptedData()).executes({
            logE(it.localizedMessage)
        }, {
            it?.let { data ->
                when {
                    data.status == "200" -> {
                        val tempGetData: String = gson.fromJson(gson.toJson(FTAes.decrypt(data.data, encriptionKey)), String::class.java)
                        val dataParsed: GeneralData = gson.fromJson(tempGetData, GeneralData::class.java)
                        view()?.onSuccessGetData(dataParsed)
                        view()?.onSuccessGetHeadlineData(dataParsed)

                    }
                    else -> {
                        logE(data.data + " error happen ")
                    }
                }


            }
        })
    }

    private fun encryptedData(): String {
        val jsonData = "{\n" +
                " \"page\": \"" + "1" + "\",\n" +
                " \"page_size\": \"" + "10" + "\",\n" +
                " \"device_id\": \"" + deviceId + "\",\n" +
                " \"community_id\": \"" + communityId + "\",\n" +
                " \"type\": \"" + "news" + "\",\n" +
                " \"email\": \"" + "" + "\",\n" +
                " \"keyword\": \"" + "" + "\"\n" +
                "}"
        return FTAes.encrypt(jsonData, encriptionKey)
    }
}