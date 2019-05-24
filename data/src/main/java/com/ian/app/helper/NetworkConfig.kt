package com.ian.app.helper

import android.content.Context
import com.google.gson.GsonBuilder
import com.ian.app.data.BuildConfig
import com.ian.app.helper.SecretKeyHelper.apiKey
import com.ian.app.helper.SecretKeyHelper.baseUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */

object NetworkConfig {

    const val getAllNews = "/v1/news/get-all"
    const val getAllHeadline = "/v1/news/get-headline"

    fun getRetrofit(ctx: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(getOkHttpClient(ctx))
            .build()
    }
}

private fun getOkHttpClient(ctx: Context): OkHttpClient {
    val timeOut = 60L
    return OkHttpClient.Builder()
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .connectTimeout(timeOut, TimeUnit.SECONDS)
//        .certificatePinner(
//            CertificatePinner.Builder()
//                .add("beta.smartcom.id", "sha256/3+buBfi9K+OhPLZjRhPtL5LnAgNvG1442uY3ShsPy10=")
//                .build()
//        )
        .sslSocketFactory(SSLHelper.getSSLConfig(ctx).socketFactory)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .addInterceptor(getInterceptor())
        .addInterceptor { chain ->
            val ongoing = chain.request().newBuilder()
            ongoing.addHeader("Content-Type", "application/json")
            ongoing.addHeader("api-key", apiKey)
            chain.proceed(ongoing.build())
        }
        .build()
}

private fun getInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
}
