package com.example.imdbsearch.di

import android.content.Context

import androidx.room.Room
import com.example.imdbsearch.common.Constants
import com.example.imdbsearch.data.local.dao.MovieDetailsDao
import com.example.imdbsearch.data.local.dao.MovieInSearchDao
import com.example.imdbsearch.data.remote.ImdbApi
import com.example.imdbsearch.data.remote.inteceptor.QueryInterceptor
import com.example.imdbsearch.data.repository.MovieDetailsRepositoryImpl
import com.example.imdbsearch.data.repository.MovieSearchRepositoryImpl
import com.example.imdbsearch.domain.repository.MovieDetailsRepository
import com.example.imdbsearch.domain.repository.MovieSearchRepository
import com.example.imdbsearch.data.local.room_database.MovieDetailsDatabase
import com.example.imdbsearch.data.local.room_database.MovieInSearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @dagger.Provides
    @javax.inject.Singleton
    fun provideApi():ImdbApi
    {
        val client= okhttp3.OkHttpClient.Builder()

        val queryInterceptor= QueryInterceptor()
        client.addInterceptor(queryInterceptor)

        val logging= okhttp3.logging.HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        client.addInterceptor(logging)

        return retrofit2.Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImdbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(api:ImdbApi,dao: MovieInSearchDao):MovieSearchRepository
    {
        return MovieSearchRepositoryImpl(api,dao)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(api:ImdbApi,dao: MovieDetailsDao): MovieDetailsRepository
    {
        return MovieDetailsRepositoryImpl(api,dao)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsDao(movieDetailsDatabase: MovieDetailsDatabase):MovieDetailsDao
    {
        return movieDetailsDatabase.movieDetailsDao()
    }

    @Provides
    @Singleton
    fun provideMovieInSearchDao(movieInSearchDatabase: MovieInSearchDatabase):MovieInSearchDao
    {
        return movieInSearchDatabase.movieInSearchDao()
    }

    @Provides
    @Singleton
    fun provideMovieInSearchDatabase(@ApplicationContext appContext: Context): MovieInSearchDatabase
    {
        return Room.databaseBuilder(
            appContext,
            MovieInSearchDatabase::class.java,
            "movie_in_search_database"
        ).allowMainThreadQueries() //TODO SOR
            .build()
    }
    @Provides
    @Singleton
    fun provideMovieDetailsDatabase(@ApplicationContext appContext: Context): MovieDetailsDatabase
    {
        return Room.databaseBuilder(
            appContext,
            MovieDetailsDatabase::class.java,
            "movie_details_database"
        ).allowMainThreadQueries() //TODO SOR
            .build()
    }
}
