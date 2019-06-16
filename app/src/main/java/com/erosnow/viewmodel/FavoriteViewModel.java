package com.erosnow.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.erosnow.services.repository.MovieRepository;
import com.erosnow.services.model.Movie;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private MutableLiveData<List<Movie>> mutableFavMovieLiveData;
    private MovieRepository movieRepository;

    private Application application;

    public FavoriteViewModel(@NonNull Application application) {

        super(application);
        this.application = application;
    }


    public void init(){
        if(mutableFavMovieLiveData !=null)
        {
            return;
        }
        movieRepository = MovieRepository.getInstance(application);

    }


    public MutableLiveData<List<Movie>>getMutableFavMovieLiveData() {
        mutableFavMovieLiveData =movieRepository.getFavMovies();
        return mutableFavMovieLiveData;
    }


}
