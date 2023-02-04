/**
 * 
 */
package com.promineotech.movies.dao;

import java.util.List;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;

public interface MoviesDao {

  List<Movies> getMovies(MovieGenre genre, String director);
  int createMovie(String title, String runtime, String release_date, String genre, String director);
  int deleteMovieByID(int id);
  int updateMovieByID(String title, int id);
}
