package com.example.imdbsearch.presentation.movie_details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.common.Response
import com.example.imdbsearch.domain.model.MovieDetails
import com.example.imdbsearch.domain.use_case.AddNewMovieDetailsToDatabaseUseCase
import com.example.imdbsearch.domain.use_case.GetMovieDetailsByIdFromDatabaseUseCase
import com.example.imdbsearch.domain.use_case.GetMovieDetailsByIdUseCase
import com.example.imdbsearch.presentation.movie_search_screen.MovieSearchState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsByIdUseCase: GetMovieDetailsByIdUseCase,
    private val getMovieDetailsByIdFromDatabaseUseCase: GetMovieDetailsByIdFromDatabaseUseCase,
    private val addNewMovieDetailsToDatabaseUseCase: AddNewMovieDetailsToDatabaseUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _state= mutableStateOf<MovieDetailsState>(MovieDetailsState())
    val state: State<MovieDetailsState> = _state
    private var id: String
    val isMovieExistInDatabase = mutableStateOf(true)

    init {
        savedStateHandle.get<String>(Constants.ID).let {
            id=it!!
            getMovieById(id)
        }

    }
    private fun getMovieById(
        id:String
    )
    {
        getMovieDetailsByIdUseCase(id=id).onEach {
            when(it)
            {
                is Response.Success->
                {
                    _state.value= MovieDetailsState(movieDetailsResult = it.data)
                    addNewMovieDetailsToDatabaseUseCase(it.data!!)
                }
                is Response.Error->
                {
                    _state.value=MovieDetailsState(error = it.message?:"MovieDetails Error")
                }
                is Response.Loading->
                {
                    _state.value= MovieDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMovieByIdFromDatabase()
    {
        getMovieDetailsByIdFromDatabaseUseCase(id=id).onEach {
            when(it) {
                is Response.Success -> {
                    _state.value = MovieDetailsState(movieDetailsResult = it.data)
                }
                is Response.Error -> {
                    _state.value = MovieDetailsState(error = it.message ?: "MovieDetails Error")
                    isMovieExistInDatabase.value=false
                }
                is Response.Loading -> {
                    _state.value = MovieDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}