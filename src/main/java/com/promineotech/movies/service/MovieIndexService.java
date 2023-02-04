/**
 * 
 */
package com.promineotech.movies.service;

import java.util.List;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;

//contains all crud operation methods

public interface MovieIndexService {
  
  List<Movies> getMovies(MovieGenre genre, String director);
  int createMovie(String title, String runtime, String release_date, String genre, String director);
  void deleteMovieByID(int id);
  int updateMovieByID(String title, int id);
}
