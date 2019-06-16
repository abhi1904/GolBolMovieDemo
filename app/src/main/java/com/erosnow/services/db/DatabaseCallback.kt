package com.erosnow.services.db

import com.erosnow.services.model.Movie

interface DatabaseCallback {


    fun onFavoriteMovieLoaded(users: List<Movie>)
    fun onUserAdded()

    fun onDataNotAvailable()

}
