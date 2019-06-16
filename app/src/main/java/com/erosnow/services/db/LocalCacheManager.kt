package com.erosnow.services.db

import android.arch.persistence.room.Room
import android.content.Context
import com.erosnow.services.model.Movie
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LocalCacheManager(private val context: Context) {
    private val db: AppDatabase? = null
    private val movieDao: MovieDBDao

    init {
        movieDao = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()   //Allows room to do operation on main thread
                .build().movieDao
    }


    fun saveMovieInfo(movie: Movie, databaseCallback: DatabaseCallback) {
        Completable.fromAction { movieDao.saveMovie(movie) }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onComplete() {
                        databaseCallback.onUserAdded()
                    }

                    override fun onError(e: Throwable) {
                        databaseCallback.onDataNotAvailable()
                    }
                })
    }

    fun getFavoriteMovie(databaseCallback: DatabaseCallback) {
        movieDao.favMovies.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { users -> databaseCallback.onFavoriteMovieLoaded(users) }
    }

    companion object {
        private val DB_NAME = "db-movies"
        private var _instance: LocalCacheManager? = null


        fun getInstance(context: Context): LocalCacheManager {
            if (_instance == null) {
                _instance = LocalCacheManager(context)
            }
            return _instance as LocalCacheManager
        }
    }


}
