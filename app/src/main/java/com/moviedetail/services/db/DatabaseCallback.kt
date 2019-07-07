package com.moviedetail.services.db

import com.moviedetail.services.model.Movie

interface DatabaseCallback {


    fun onFavoriteMovieLoaded(users: List<Movie>)
    fun onUserAdded()

    fun onDataNotAvailable()

}
