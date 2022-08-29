package com.example.imdbsearch.presentation.movie_search_screen.components


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.imdbsearch.presentation.Screen
import com.example.imdbsearch.presentation.movie_search_screen.MovieSearchViewModel

@Composable
fun MovieSearchScreen(
    navController: NavController,
    viewModel:MovieSearchViewModel= hiltViewModel()
)
{   
    val state=viewModel.state.value
//    val state= MovieSearchState(null,"")

    val isLoadMoreButtonVisible by remember {
        viewModel.isLoadMoreButtonEnabled
    }
    val isLoadingMore by remember {
        viewModel.isLoadingMore
    }

    val scrollState = rememberScrollState()


//    SwipeRefresh(state = rememberSwipeRefreshState(viewModel.isRefreshing.value), onRefresh = { viewModel.loadMovieSearchFromDatabase() }
//    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
            SearchComponent(navController)

            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    for (movieSearchItem in state.movieSearchResult?.Search?: listOf())
                    {
                        MovieListItem(
                            movieInSearch = movieSearchItem,
                            onClicked = { id->
                                navController.navigate(Screen.MovieDetailsScreen.route+"/$id")
                            }
                        )
                    }
                    if(isLoadMoreButtonVisible)
                    {
                        Button(
                            modifier=Modifier.align(Alignment.CenterHorizontally),
                            onClick = {
                                viewModel.getMoreMovieSearch()
                            }) {
                            if (isLoadingMore)
                            {
                                CircularProgressIndicator(color = Color.White)
                            }
                            else Text("Load More")
                        }
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
                    Toast.makeText(LocalContext.current,state.error+"\nPrevious Results are shown.",Toast.LENGTH_SHORT).show()
                    viewModel.loadMovieSearchFromDatabase()
                }
            }

        }
//    }

}

@Preview(showBackground = true)
@Composable
fun MovieSearchScreenPreview()
{
    MovieSearchScreen(rememberNavController())
}