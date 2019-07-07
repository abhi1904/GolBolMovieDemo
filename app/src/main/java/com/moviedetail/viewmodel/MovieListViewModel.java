package com.moviedetail.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.moviedetail.services.repository.MovieRepository;
import com.moviedetail.services.model.Movie;
import com.moviedetail.services.model.SearchMovies;

import java.util.Date;

public class MovieListViewModel extends AndroidViewModel {
    private MutableLiveData<SearchMovies> mutableMovieLiveData;
    private MutableLiveData<Boolean> objectMutableLiveData = new MutableLiveData<>();
    private MovieRepository movieRepository;
    private Application application;
    private Context context;

    public MovieListViewModel(@NonNull Application application) {

        super(application);
        this.application = application;
    }


    public void init() {
        if (mutableMovieLiveData != null) {
            return;
        }
        movieRepository = MovieRepository.getInstance(application);

    }

    public MutableLiveData<Boolean> insertMovieInfo(Movie movie) {
        movie.setCreated_at(new Date());
        objectMutableLiveData = movieRepository.saveMovieInfo(movie);
        return objectMutableLiveData;

    }


    public MutableLiveData<SearchMovies> getMoviesRepository(String query, int page) {
        mutableMovieLiveData = movieRepository.getTopRatedMovies(query, page);

        return mutableMovieLiveData;
    }


}