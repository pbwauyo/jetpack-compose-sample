package com.peter.beautiful.domain.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    @SerializedName("page") val status: String = "",
    @SerializedName("results") val moviesList: List<Movie> = listOf()
)