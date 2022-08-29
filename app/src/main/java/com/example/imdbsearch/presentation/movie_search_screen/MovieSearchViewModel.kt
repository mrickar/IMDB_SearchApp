package com.example.imdbsearch.presentation.movie_search_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.common.Response
import com.example.imdbsearch.data.remote.dto.plus
import com.example.imdbsearch.domain.use_case.CreateMovieSearchDatabaseUseCase
import com.example.imdbsearch.domain.use_case.GetMovieSearchFromDatabaseUseCase
import com.example.imdbsearch.domain.use_case.GetMovieSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieSearchUseCase: GetMovieSearchUseCase,
    private val getMovieSearchFromDatabaseUseCase: GetMovieSearchFromDatabaseUseCase,
    private val createMovieSearchDatabaseUseCase: CreateMovieSearchDatabaseUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _state= mutableStateOf<MovieSearchState>(MovieSearchState())
    val state: State<MovieSearchState> = _state
    val isLoadMoreButtonEnabled = mutableStateOf(false)
    val isLoadingMore = mutableStateOf(false)

    private var pageNum = mutableStateOf(1)
    private var searchTitle:String?=null
    private var type:String?=null
    private var year:String?=null
    init {

        searchTitle=savedStateHandle.get<String?>(Constants.SEARCH_TITLE)
        type=savedStateHandle.get<String?>(Constants.TYPE)
        year=savedStateHandle.get<String?>(Constants.YEAR)

        if(searchTitle!=null)
        {
            getMovieSearch(searchTitle=searchTitle!!, type = type, year = year)
        }
    }
    fun loadMovieSearchFromDatabase()
    {
        getMovieSearchFromDatabaseUseCase().onEach {
            when(it)
            {
                is Response.Success->
                {
                    _state.value=MovieSearchState(movieSearchResult = it.data)
                }
                is Response.Error->
                {
                    Log.d("MovieSearchVM","loadMovieFromDBError")
                    _state.value=MovieSearchState(error = it.message?:"MovieSearchVM Error")
                }
                is Response.Loading->
                {
                    _state.value= MovieSearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun getMoreMovieSearch()
    {
        getMovieSearchUseCase(searchTitle = searchTitle!!,type=type,year= year, pageNum = (pageNum.value++).toString()).onEach {
            when(it)
            {
                is Response.Success->
                {
                    isLoadingMore.value=false
                    if(it.data!!.Response!="False")
                    {
                        _state.value=MovieSearchState(movieSearchResult = _state.value.movieSearchResult?.plus(it.data))
                        createMovieSearchDatabaseUseCase(it.data)
                    }
                    else
                    {
                        isLoadMoreButtonEnabled.value=false
                        pageNum.value--
                    }
                }
                is Response.Error->
                {
                    isLoadingMore.value=false
                    _state.value=MovieSearchState(error = it.message?:"MovieSearchVM Error")
                }
                is Response.Loading->
                {
                    isLoadingMore.value=true
                    _state.value.isLoading=true
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getMovieSearch(
        searchTitle: String,
        type: String? = null,
        year: String? = null,
    )
    {
        getMovieSearchUseCase(searchTitle = searchTitle,type=type,year= year, pageNum = (pageNum.value++).toString()).onEach {
            when(it)
            {
                is Response.Success->
                {
                    if(it.data!!.Response=="False")
                    {
                        _state.value=MovieSearchState(error = it.message?:"Bad Request")
                    }
                    else
                    {
                        _state.value= MovieSearchState(movieSearchResult = it.data)
                        createMovieSearchDatabaseUseCase(it.data)
                        isLoadMoreButtonEnabled.value=true
                    }
                }
                is Response.Error->
                {
                    _state.value=MovieSearchState(error = it.message?:"MovieSearchVM Error")
                }
                is Response.Loading->
                {
                    _state.value= MovieSearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}


