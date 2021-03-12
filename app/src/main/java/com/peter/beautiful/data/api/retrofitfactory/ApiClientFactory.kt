package com.peter.beautiful.data.api.retrofitfactory

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object ApiClientFactory {
    private var client: OkHttpClient? = null

    fun create(): OkHttpClient{
        Log.d("CREATING CLIENT", "STARTED")
        return if (client == null){
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.apply {
                connectTimeout(15, TimeUnit.SECONDS)
                readTimeout(15, TimeUnit.SECONDS)
                addInterceptor(interceptor)
            }
            clientBuilder.build()
        }
        else{
            client!!
        }
    }
}