package com.moviedetail.services.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SearchMovies implements Parcelable {

    public static final Creator<SearchMovies> CREATOR = new Creator<SearchMovies>() {
        @Override
        public SearchMovies createFromParcel(Parcel in) {
            return new SearchMovies(in);
        }

        @Override
        public SearchMovies[] newArray(int size) {
            return new SearchMovies[size];
        }
    };
    int page;
    int total_results;
    int total_pages;
    List<Movie> results;

    public SearchMovies() {

    }

    protected SearchMovies(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
        results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static Creator<SearchMovies> getCREATOR() {
        return CREATOR;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(total_results);
        dest.writeInt(total_pages);
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
