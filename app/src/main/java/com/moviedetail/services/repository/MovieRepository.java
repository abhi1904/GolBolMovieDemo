package com.moviedetail.services.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.moviedetail.services.db.DatabaseCallback;
import com.moviedetail.services.db.LocalCacheManager;
import com.moviedetail.services.MovieApi;
import com.moviedetail.services.RetrofitClientInstance;
import com.moviedetail.services.model.Movie;
import com.moviedetail.services.model.SearchMovies;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.moviedetail.services.RetrofitClientInstance.getRetrofitInstance;


public class MovieRepository {
    private static MovieRepository movieRepository;
    private MovieApi movieApiService;
    private Application application;


    public MovieRepository(Application application) {
        this.application = application;
        movieApiService = getRetrofitInstance().create(MovieApi.class);
    }


    public static MovieRepository getInstance(Application application) {
        if (movieRepository == null) {
            movieRepository = new MovieRepository(application);
        }
        return movieRepository;
    }


    public MutableLiveData<SearchMovies> getTopRatedMovies(String query, int page) {
        final MutableLiveData<SearchMovies> data = new MutableLiveData<>();

        movieApiService.getTopRatedMovies(RetrofitClientInstance.API_KEY, query, page).enqueue(new Callback<SearchMovies>() {
            @Override
            public void onResponse(Call<SearchMovies> call, Response<SearchMovies> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SearchMovies> call, Throwable t) {
                data.setValue(null);
            }
        });


        return data;
    }


    public MutableLiveData<Boolean> saveMovieInfo(Movie movie) {

        final MutableLiveData<Boolean> tMutableLiveData = new MutableLiveData<>();

        LocalCacheManager.Companion.getInstance(application).saveMovieInfo(movie, new DatabaseCallback() {
            @Override
            public void onFavoriteMovieLoaded(@NotNull List<? extends Movie> users) {

            }

            @Override
            public void onUserAdded() {
                tMutableLiveData.setValue(true);
            }

            @Override
            public void onDataNotAvailable() {
                tMutableLiveData.setValue(false);
            }
        });
        return tMutableLiveData;

    }


    public MutableLiveData<List<Movie>> getFavMovies() {

        final MutableLiveData<List<Movie>> movieMutableLiveData = new MutableLiveData<>();

        LocalCacheManager.Companion.getInstance(application).getFavoriteMovie(new DatabaseCallback() {
            @Override
            public void onFavoriteMovieLoaded(@NotNull List<? extends Movie> users) {
                movieMutableLiveData.setValue((List<Movie>) users);

            }

            @Override
            public void onUserAdded() {
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
        return movieMutableLiveData;

    }
}