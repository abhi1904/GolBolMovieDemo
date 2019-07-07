package com.moviedetail.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviedetail.R;
import com.moviedetail.services.model.Movie;
import com.moviedetail.utils.Utils;
import com.moviedetail.viewmodel.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;


public class FavoriteMovieFragment extends Fragment implements OnItemClicked {

    private RecyclerView recyclerView;

    private MoviesAdapter mTopRatedMoviesAdapter;
    private FavoriteViewModel favoriteViewModel;
    private ArrayList moviesList = new ArrayList<>();
    private Context mContext;

    public static FavoriteMovieFragment newInstance() {
        FavoriteMovieFragment fragment = new FavoriteMovieFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        initView();

        return root;
    }

    private void initView() {
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.init();

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        mTopRatedMoviesAdapter = new MoviesAdapter(getActivity(), moviesList, this, Utils.INSTANCE.getDeviceWidth(getActivity()));
        recyclerView.setAdapter(mTopRatedMoviesAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);


    }

    @Override
    public void invoke(Movie movie, String type) {


        Intent intent = new Intent(mContext, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            favoriteViewModel.getMutableFavMovieLiveData().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(@Nullable List<Movie> movies) {


                    moviesList.clear();
                    if (movies != null && movies.size() > 0) {

                        moviesList.addAll(movies);
                        mTopRatedMoviesAdapter.notifyDataSetChanged();

                    }
                }
            });
        } else {
        }
    }

}