package com.ian.app.model

import com.google.gson.annotations.SerializedName

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
data class GeneralData(@field:SerializedName("list") var data: ListNewsItem?) {
    data class ListNewsItem(
        @field:SerializedName("all") var all: List<NewsModel>?,
        @field:SerializedName("news") var news: List<NewsModel>?,
        @field:SerializedName("article") var article: List<NewsModel>?
    )
}