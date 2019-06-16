package com.erosnow.view;

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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.erosnow.R;
import com.erosnow.services.model.Movie;
import com.erosnow.services.model.SearchMovies;
import com.erosnow.utils.Utils;
import com.erosnow.viewmodel.MovieListViewModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment implements OnItemClicked {

    boolean isLoading = false;
    private RecyclerView recyclerView;
    private MoviesAdapter mTopRatedMoviesAdapter;
    private MovieListViewModel movieListViewModel;
    private ArrayList<Movie> moviesList = new ArrayList<Movie>();
    private Context mContext;
    private ProgressBar progressBar;
    private SearchMovies topRatedMovie;
    private String mQuery;
    private int visibleThreshold = 2;

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true); // Add this!
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        recyclerView = root.findViewById(R.id.recyclerView);
        progressBar = root.findViewById(R.id.item_progress_bar);
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        movieListViewModel.init();


        setupRecyclerView();

    }

    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        mTopRatedMoviesAdapter = new MoviesAdapter(getActivity(), moviesList, this, Utils.INSTANCE.getDeviceWidth(getActivity()));
        recyclerView.setAdapter(mTopRatedMoviesAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        initScrollListener();

    }

    private void loadMore(String query, int pageno) {
        moviesList.add(null);
        mTopRatedMoviesAdapter.notifyItemInserted(moviesList.size() - 1);

        movieListViewModel.getMoviesRepository(query, pageno).observe(this, new Observer<SearchMovies>() {
            @Override
            public void onChanged(@Nullable SearchMovies movies) {

                if (movies != null) {

                    moviesList.remove(moviesList.size() - 1);
                    int scrollPosition = moviesList.size();
                    mTopRatedMoviesAdapter.notifyItemRemoved(scrollPosition);
                    isLoading = false;
                    topRatedMovie = movies;
                    moviesList.addAll(movies.getResults());
                    mTopRatedMoviesAdapter.notifyDataSetChanged();
                } else {
                    moviesList.remove(moviesList.size() - 1);
                    int scrollPosition = moviesList.size();
                    mTopRatedMoviesAdapter.notifyItemRemoved(scrollPosition);
                    Toast.makeText(mContext, "server error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void loadMoreFirst(String query, int pageno) {
        progressBar.setVisibility(View.VISIBLE);

        movieListViewModel.getMoviesRepository(query, pageno).observe(this, new Observer<SearchMovies>() {
            @Override
            public void onChanged(@Nullable SearchMovies movies) {

                if (movies != null) {

                    if(movies.getResults().size()>0) {
                        topRatedMovie = movies;
                        moviesList.clear();
                        moviesList.addAll(movies.getResults());
                        mTopRatedMoviesAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Toast.makeText(mContext, "No result found", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(mContext, "Please try after sometime", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void invoke(Movie movie, String type) {

        if (!"Detail".equalsIgnoreCase(type)) {
            movieListViewModel.insertMovieInfo(movie).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    if (aBoolean) {
                        Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        } else {
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();

        inflater.inflate(R.menu.menusearch, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() < 4) {
                    Toast.makeText(mContext, "Please enter more than 3 letters", Toast.LENGTH_LONG).show();

                    return true;
                } else {
                    mQuery = query;
                    moviesList.clear();
                    loadMoreFirst(mQuery, 1);

                    return false;
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Here is where we are going to implement the filter logic
                return true;
            }

        });
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (topRatedMovie.getPage() <= topRatedMovie.getTotal_pages()) {
                        loadMore(mQuery, topRatedMovie.getPage() + 1);
                        isLoading = true;


                    }
                }
            }
        });


    }


}