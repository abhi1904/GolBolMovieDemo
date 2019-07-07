package com.moviedetail.services;


import com.moviedetail.services.model.SearchMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("search/movie")
    Call<SearchMovies> getTopRatedMovies(@Query("api_key") String apikey, @Query("query") String query, @Query("page") int pageNo);

}
