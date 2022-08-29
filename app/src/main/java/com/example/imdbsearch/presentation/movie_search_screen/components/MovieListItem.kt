package com.example.imdbsearch.presentation.movie_search_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.data.remote.dto.MovieInSearch


@Composable
fun MovieListItem(
    movieInSearch: MovieInSearch,
    onClicked:((String) ->Unit)?
)
{
    Card(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                if (onClicked != null) {
                    onClicked(movieInSearch.imdbID)
                }
            },
        elevation = 4.dp
    )
    {
        Row(
            modifier = Modifier,
            ) {
            AsyncImage(modifier = Modifier.width(120.dp), model = movieInSearch.Poster, contentDescription = null, contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier) {
                Text(text = movieInSearch.Title, overflow = TextOverflow.Ellipsis, maxLines = 1)
                Text(text=movieInSearch.Year,fontStyle = FontStyle.Italic)
                Text(text = movieInSearch.Type, fontStyle = FontStyle.Italic,color = Color.LightGray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListItemPreview()
{
    MovieListItem(Constants.MOVIEINSEARCH_EXP,null)
}