package com.peter.beautiful.data.api.retrofitfactory

import android.util.Log
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private val retrofit: Retrofit? = null

    fun create(): Retrofit{
        Log.d("CREATING RETROFIT", "STARTED")
        return if(retrofit == null){
            val newRetrofit = Retrofit.Builder().apply {
                baseUrl("https://api.themoviedb.org/3/")
                addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                addConverterFactory(GsonConverterFactory.create())
                client(ApiClientFactory.create())
            }
            newRetrofit.build()
        }else{
            retrofit
        }
    }
}

