package com.example.imdbsearch.presentation.movie_details_screen.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imdbsearch.R
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.presentation.movie_details_screen.MovieDetailsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imdbsearch.presentation.movie_details_screen.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel()
){
    val state=viewModel.state.value

//    val state= MovieDetailsState(movieDetailsResult = Constants.MOVIE_DETAILS_EXP)
    val isMovieExistInDatabase by remember{viewModel.isMovieExistInDatabase}
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),)
    {
        if(state.movieDetailsResult!=null)
        {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                //TITLE
                Text(
                    modifier=Modifier.align(Alignment.CenterHorizontally),
                    text = state.movieDetailsResult.Title,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(16.dp))

                //POSTER ROW
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
                    AsyncImage(modifier = Modifier.width(120.dp), model = state.movieDetailsResult.Poster, contentDescription = null, contentScale = ContentScale.FillWidth, placeholder = painterResource(
                        id = (R.drawable.gecicifilmfoto)
                    ))
                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        val styleList= listOf<TextStyle?>(TextStyle(fontWeight = FontWeight.Bold),null)
                        PartlyStyledText(textList = listOf("Type: ",state.movieDetailsResult.Type), styleList =styleList )
                        PartlyStyledText(textList = listOf("Genre: ",state.movieDetailsResult.Genre), styleList =styleList )
                        PartlyStyledText(textList = listOf("Language: ",state.movieDetailsResult.Language), styleList =styleList )
                        PartlyStyledText(textList = listOf("Length: ",state.movieDetailsResult.Length), styleList =styleList )
                        PartlyStyledText(textList = listOf("Released: ",state.movieDetailsResult.Released), styleList =styleList )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Plot", style = TextStyle(fontWeight = FontWeight.Bold))
                Text(text = state.movieDetailsResult.Plot)
                Spacer(modifier = Modifier.height(16.dp))

                //DIRECTOR+RATING ROW
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start
                )
                {
                    val styleList= listOf<TextStyle?>(TextStyle(fontWeight = FontWeight.Bold),null)

                    //DIRECTOR+ACTORS
                    Column(modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)) {

                        PartlyStyledText(textList = listOf("Director: ",state.movieDetailsResult.Director), styleList =styleList )
                        Text(text = "Actors", style = TextStyle(fontWeight = FontWeight.Bold))
                        Text(text=state.movieDetailsResult.Actors)
                    }

                    //RATINGS
                    Column(modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1.5f)
                        .padding(8.dp)
                    ) {
                        Text(text = "Ratings", style = TextStyle(fontWeight = FontWeight.Bold))
                        Divider()
                        PartlyStyledText(textList = listOf("IMDB: ",state.movieDetailsResult.imdbRating), styleList =styleList )
                        PartlyStyledText(textList = listOf("MetaScore: ",state.movieDetailsResult.MetaScore), styleList =styleList )
                        for( rating in state.movieDetailsResult.Ratings)
                        {
                            PartlyStyledText(textList = listOf("${rating.Source}: ",rating.Value), styleList =styleList)
                        }
                    }
                }
                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Awards", style = TextStyle(fontWeight = FontWeight.Bold))
                Text(text = state.movieDetailsResult.Awards)
            }
        }
        if(state.isLoading)
        {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        else if (state.error.isNotBlank())
        {
            Toast.makeText(LocalContext.current,state.error, Toast.LENGTH_SHORT).show()
            if(isMovieExistInDatabase) viewModel.getMovieByIdFromDatabase()
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenPreview(){
    MovieDetailsScreen()
}

@Composable
fun PartlyStyledText(
    textList: List<String>,
    styleList:List<TextStyle?>
){
    Row(modifier=Modifier.fillMaxWidth())
    {
        for(i in textList.indices)
        {
            Text(
                modifier=Modifier,
                text = textList[i],
                style = if(styleList[i]!=null) styleList[i]!! else TextStyle.Default,
            )
        }
    }
}


