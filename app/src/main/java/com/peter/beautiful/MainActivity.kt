package com.peter.beautiful

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.peter.beautiful.domain.models.Movie
import com.peter.beautiful.domain.models.MoviesResponse
import com.peter.beautiful.ui.theme.BeautifulTheme
import com.peter.beautiful.ui.theme.Purple200
import com.peter.beautiful.ui.viewmodels.MoviesViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

class MainActivity : AppCompatActivity() {
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            BeautifulTheme(darkTheme = false) {
                val moviesResponse: MoviesResponse by moviesViewModel.moviesLiveData.observeAsState(
                    initial = MoviesResponse()
                )

                Scaffold(backgroundColor = Color.White) {

                    if(moviesResponse.moviesList.isEmpty()){
                        Text(
                            text = "No magazines to show",
                        )
                    }else{
                        MoviesList(movies = moviesResponse.moviesList)
                    }
                }
            }
        }
    }
}

@Composable
fun MoviesList(movies: List<Movie>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)){
        items(movies){
            movie -> MagazineCard(movie = movie)
        }
    }
}

@Composable
fun MagazineCard(movie: Movie){
    Card(
        elevation = 6.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start, ){
            Image(
                painter = painterResource(id = R.drawable.bris_hand_holding),
                contentDescription = "Magazine image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(150.dp),
                alignment = Alignment.Center
            )
            Text(text = movie.title, style = MaterialTheme.typography.h5, maxLines = 1)
            Text(text = movie.overview, style = MaterialTheme.typography.h6, maxLines = 3)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BeautifulTheme {
//        MagazinesList(name = "Peter")
    }
}