/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package data;

import com.erosnow.services.model.Movie;
import com.erosnow.services.model.SearchMovies;

import java.util.ArrayList;
import java.util.List;

public class FakeRandomUserGeneratorAPI {

  private static final int PAGE  = 1;
  private static final int TOTAL_RESULTS = 47;
  private static final int TOTAL_PAGES =3;
  private static final int VOTE_COUNT = 6765;
  private static final int ID = 299534;
  private static final boolean VIDEO = false;
  private static final double VOTE_AVG = 8.4;
  private static final String TITLE = "Avengers: Endgame";
  private static final double POPULARITY = 106.389;
  private static final String POSTER_PATH  = "/or06FN3Dka5tukK1e9sl16pB3iy.jpg";

  private static final String ORIGINAL_LANGUAGE = "en";
  private static final String ORIGINAL_TITLE = "Avengers: Endgame";
  //private static final int[] GENRE_IDS = {12,878,28};
  private static final String BACKDROP_PATH = "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg";
  private static final boolean ADULT = false;
  private static final String OVERVIEW = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.";
  private static final String RELEASE_DATE = "2019-04-24";

/*Return list of movies*/
  public static SearchMovies getSearchMovie() {

    SearchMovies topRatedMovie = new SearchMovies();
    topRatedMovie.setPage(PAGE);
    topRatedMovie.setTotal_results(TOTAL_RESULTS);
    topRatedMovie.setTotal_pages(TOTAL_PAGES);

    List<Movie> movieList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      movieList.add(getMovie());
    }
    topRatedMovie.setResults(movieList);
    return topRatedMovie;
  }

  public static Movie getMovie() {
    Movie movie = new Movie();
    movie.setVote_count(VOTE_COUNT);
    movie.setId(ID);
    movie.setVideo(VIDEO);
    movie.setVote_average((float) VOTE_AVG);
    movie.setTitle(TITLE);
    movie.setPopularity((float) POPULARITY);
    movie.setPoster_path(POSTER_PATH);
    movie.setOriginal_language(ORIGINAL_LANGUAGE);
    movie.setOriginal_title(ORIGINAL_TITLE);
    movie.setBackdrop_path(BACKDROP_PATH);
    movie.setAdult(ADULT);
    movie.setOverview(OVERVIEW);
    movie.setRelease_date(RELEASE_DATE);

    return movie;
  }
}
