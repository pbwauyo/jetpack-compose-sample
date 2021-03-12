package com.peter.beautiful.data.api

import com.peter.beautiful.data.api.retrofitfactory.RetrofitFactory
import com.peter.beautiful.domain.models.MoviesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MagazineApi {
    @GET("movie/popular?language=en-US&page=1")
    fun getAllMagazines(@Query("api_key") apiKey: String): Observable<MoviesResponse>

    companion object {
        fun create(): MagazineApi {
            val retrofit = RetrofitFactory.create()
            return retrofit.create(MagazineApi::class.java)
        }
    }
}
