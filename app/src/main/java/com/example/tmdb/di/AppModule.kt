package com.example.tmdb.di

import com.example.tmdb.data.discover.DiscoverInteractor
import com.example.tmdb.data.discover.DiscoverLocalDataSource
import com.example.tmdb.data.discover.DiscoverRemoteDataSourceImpl
import com.example.tmdb.data.discover.DiscoverRepository
import com.example.tmdb.data.local.MoviesCategoriesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun getTMDBRetrofit(): TMDBRetrofit = TMDBRetrofit(GsonBuilder().create())
//
//    @Provides
//    fun getTMDBService(retrofit: TMDBRetrofit): TmdbService = retrofit.create(TmdbService::class.java)

    @Singleton
    @Provides
    fun getDiscoverRemoteDataSourceImpl(/*service: TmdbService*/): DiscoverRemoteDataSourceImpl {
//        val retrofitService = TMDBRetrofit(GsonBuilder().create())
        return DiscoverRemoteDataSourceImpl(/*retrofitService.create(TmdbService::class.java)*/)
    }



    @Singleton
    @Provides
    fun getDiscoverInteractor(discoverRepository: DiscoverRepository): DiscoverInteractor =
        DiscoverInteractor(discoverRepository)

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
}