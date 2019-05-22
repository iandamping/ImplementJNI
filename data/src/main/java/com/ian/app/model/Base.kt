package com.ian.app.model

import com.google.gson.annotations.SerializedName

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
data class Base<T>(
    @field:SerializedName("data") var data: T,
    @field:SerializedName("status") var status: String?,
    @field:SerializedName("message") var message: String?
) {
}