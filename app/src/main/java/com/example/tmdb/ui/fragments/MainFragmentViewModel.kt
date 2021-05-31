package com.example.tmdb.ui.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.discover.DiscoverInteractor
import com.example.tmdb.data.discover.DiscoverRepository
import com.example.tmdb.data.discover.entities.enums.MoviesCategory
import com.example.tmdb.data.local.MoviesCategoriesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val discoverInteractor: DiscoverInteractor,
    private val moviesCategoriesProvider: MoviesCategoriesProvider,
    private val discoverRepository: DiscoverRepository
) :
    ViewModel() {
    fun wefwef() {

        Log.e("MainFragmentViewModel", "wefwef: ")
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            discoverInteractor.getMainScreenData(viewModelScope, moviesCategoriesProvider.getMoviesCategories())
                .collect { resource ->

                    Log.e("MainFragmentViewModel", ": ")
                    /*when (resource.status) {
                        Resource.Status.SUCCESS -> {
                            val response = resource.data
                            val movies = response?.toResult()
                            Log.e("MainFragmentViewModel", ": ")
                        }
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.ERROR -> {

                        }
                    }*/
                }
        }
    }

//    private fun getMainScreenMoviesCategoriesList(): List<MoviesCategory> {
//
//
//    }

}