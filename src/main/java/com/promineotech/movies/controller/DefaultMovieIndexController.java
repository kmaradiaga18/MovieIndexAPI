/**
 * 
 */
package com.promineotech.movies.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;
import com.promineotech.movies.service.MovieIndexService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultMovieIndexController implements MovieIndexController{
  
  @Autowired
  private MovieIndexService movieIndexService;
  
  @Override
  public List<Movies> getMovies(MovieGenre genre, String director) {
    log.debug("Genre{}, Director{}",genre, director);
    return movieIndexService.getMovies(genre, director);
  }

}
