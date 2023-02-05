package com.promineotech.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.movies.entity.Movies;
import com.promineotech.movies.entity.NewMovieRequest;
import com.promineotech.movies.service.MovieIndexService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicNewMovieController implements NewMovieController
{
  @Autowired
  private MovieIndexService movieIndexService;
  
  @Override
  public int createNewMovie(NewMovieRequest newMovieRequest) {
    log.debug("NewMovie={}", newMovieRequest);
    return movieIndexService.createMovie(
      newMovieRequest.getTitle(), 
      newMovieRequest.getRuntime(), 
      newMovieRequest.getRelease_date(), 
      newMovieRequest.getGenre_id(),
      newMovieRequest.getDirector_id());
  }

  @Override
  public void deleteMovieByID(@PathVariable("id") int id){
    log.debug("Movie ID to Delete {}", id);
    movieIndexService.deleteMovieByID(id);
  }
}
