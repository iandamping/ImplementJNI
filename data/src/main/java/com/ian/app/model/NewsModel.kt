package com.ian.app.model

import com.google.gson.annotations.SerializedName

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
data class NewsModel(
    @field:SerializedName("news_id") val newsId: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("author") val author: String?,
    @field:SerializedName("short_description") val short_description: String?,
    @field:SerializedName("image_url") val imageUrl: String?,
    @field:SerializedName("additional_file_url") val additional_file_url: String?,
    @field:SerializedName("created_date") val created_date: String?,
    @field:SerializedName("category_id") val category_id: String?,
    @field:SerializedName("like_count") val like_count: String?,
    @field:SerializedName("comment_count") val comment_count: String?,
    @field:SerializedName("is_liked") val is_liked: String?,
    @field:SerializedName("is_headline") val is_headline: String?
) {

}