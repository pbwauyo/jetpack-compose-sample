package com.peter.beautiful.data.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.peter.beautiful.data.api.MagazineApi
import com.peter.beautiful.domain.models.MoviesResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesRepo {
    private val API_KEY = "2e52c2488957904c18efef3b56dac1c6"
    private val magazineApi by lazy {
        MagazineApi.create()
    }

    private val disposables by lazy {
        CompositeDisposable()
    }

    fun fetchMagazines(): MutableLiveData<MoviesResponse>{
        Log.d("FETCHING MAGAZINES", "STARTED")
        val moviesLiveData = MutableLiveData<MoviesResponse>()
        val moviesResponseDisposable = magazineApi.getAllMagazines(API_KEY)
            .subscribeOn(Schedulers.io())
            .subscribe (
                {
                    moviesLiveData.postValue(it)
                },
                {
                    Log.d("MAGAZINES FETCH ERROR", "${it.message}")
                }
            )

        disposables.add(moviesResponseDisposable)

        return moviesLiveData
    }

    fun dispose(){
        disposables.dispose()
    }
}