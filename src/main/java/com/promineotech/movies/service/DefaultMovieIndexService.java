/**
 * 
 */
package com.promineotech.movies.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.movies.dao.MoviesDao;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultMovieIndexService implements MovieIndexService{
  
//contains all crud operation methods
  
  @Autowired
  private MoviesDao moviesDao;
  
  @Transactional(readOnly = true)
  @Override
  public List<Movies> getMovies(MovieGenre genre, String director) {
   log.info("The getMovies method was called with genre={} and director={}",
       genre,director);
   
   List<Movies> movies = moviesDao.getMovies(genre, director);
    
   if(movies.isEmpty()) {
     String msg = String.format("No movies found with genre=%s and director=%s",genre, director);
     throw new NoSuchElementException(msg);
   }
    
    return movies;
  }
  
  @Transactional
  @Override
  public int createMovie(String title, String runtime, String release_date, String genre, String director){
    log.debug("createMovies method was called with title={}, runtime={}, release_date={}, genre={}, director={}", 
      title, runtime, release_date, genre, director);

      int createdMovie = moviesDao.createMovie(title, runtime, release_date, genre, director);
      if(createdMovie == 0) {
        String msg = String.format("Movie was not added with title=%s runtime=%s release_date=%s genre=%s and director=%s",
          title, runtime, release_date, genre, director);
        throw new NoSuchElementException(msg);
      }
      return createdMovie;
  }

  @Transactional
  @Override
  public void deleteMovieByID(int id){
    log.debug("deleteMovieByID method was called with movie_id={}", id);
    int deletedMovie = moviesDao.deleteMovieByID(id);

    if(deletedMovie == 0){
      String msg = String.format("No matching movie with movie_id found movie_id=%s", id);
      throw new NoSuchElementException(msg);
    }
  }
  
  @Transactional
  @Override
  public int updateMovieByID(String title, int id) {
    log.debug("updateMovieByID method was called with movie_id={}", id);
    int updateMovie = moviesDao.updateMovieByID(title, id);
    return updateMovie; 
  }
 
}
