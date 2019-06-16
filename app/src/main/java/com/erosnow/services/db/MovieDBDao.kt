package com.erosnow.services.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.erosnow.services.model.Movie
import io.reactivex.Flowable

@Dao
interface MovieDBDao {

    @get:Query("SELECT * FROM Movie ORDER BY created_at ASC")
    val favMovies: Flowable<List<Movie>>


    @Insert
    fun saveMovie(vararg movie: Movie)
}
