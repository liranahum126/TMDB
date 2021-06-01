package com.example.tmdb.di

import android.content.Context
import com.example.tmdb.data.discover.DiscoverInteractor
import com.example.tmdb.data.discover.DiscoverLocalDataSource
import com.example.tmdb.data.discover.DiscoverRemoteDataSourceImpl
import com.example.tmdb.data.discover.DiscoverRepository
import com.example.tmdb.data.local.MoviesCategoriesProvider
import com.example.tmdb.data.retrofit.TMDBRetrofit
import com.example.tmdb.data.services.TmdbService
import com.example.tmdb.utils.StringProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getTMDBRetrofit(): TMDBRetrofit = TMDBRetrofit(GsonBuilder().create())

    @Singleton
    @Provides
    fun getTMDBService(retrofit: TMDBRetrofit): TmdbService = retrofit.create(TmdbService::class.java)

    @Singleton
    @Provides
    fun getDiscoverRemoteDataSourceImpl(service: TmdbService): DiscoverRemoteDataSourceImpl {
        return DiscoverRemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun getDiscoverInteractor(discoverRepository: DiscoverRepository, stringProvider: StringProvider): DiscoverInteractor =
        DiscoverInteractor(discoverRepository, stringProvider)

    @Singleton
    @Provides
    fun getDiscoverRepository(
        discoverRemoteDataSourceImpl: DiscoverRemoteDataSourceImpl,
        discoverLocalDataSource: DiscoverLocalDataSource
    ): DiscoverRepository =
        DiscoverRepository(discoverRemoteDataSourceImpl, discoverLocalDataSource)

    @Singleton
    @Provides
    fun getDiscoverLocalDataSource(): DiscoverLocalDataSource = DiscoverLocalDataSource()

    @Singleton
    @Provides
    fun getMoviesCategoriesProvider() = MoviesCategoriesProvider()

    @Singleton
    @Provides
    fun getStringProvider(@ApplicationContext context: Context) = StringProvider(context)
}