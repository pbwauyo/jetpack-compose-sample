package com.peter.beautiful.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peter.beautiful.data.repos.MoviesRepo
import com.peter.beautiful.domain.models.MoviesResponse

class MoviesViewModel : ViewModel(){

    private val moviesRepo = MoviesRepo()
    var moviesLiveData = MutableLiveData<MoviesResponse>()

    init {
        moviesLiveData = moviesRepo.fetchMagazines()
    }

    override fun onCleared() {

        moviesRepo.dispose()
        super.onCleared()
    }
}