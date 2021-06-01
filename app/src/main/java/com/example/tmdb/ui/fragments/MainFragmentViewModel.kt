package com.example.tmdb.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.discover.DiscoverInteractor
import com.example.tmdb.data.discover.entities.bo.Movie
import com.example.tmdb.data.local.MoviesCategoriesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val discoverInteractor: DiscoverInteractor,
    private val moviesCategoriesProvider: MoviesCategoriesProvider
) : ViewModel() {
    // TODO: 6/1/21 create base view model

    private val mMoviesCategoriesLiveData = MutableLiveData<List<Category<Movie>>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            discoverInteractor.getMainScreenData(
                viewModelScope,
                moviesCategoriesProvider.getMoviesCategories()
            ).collect { moviesCategories ->
                moviesCategories.distinctBy {
                    it.mItems.isEmpty()
                }

                mMoviesCategoriesLiveData.postValue(moviesCategories)
            }
        }
    }

    fun getMoviesCategoriesLiveData(): LiveData<List<Category<Movie>>> {
        return mMoviesCategoriesLiveData
    }
}