package com.promineotech.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.movies.service.MovieIndexService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicUpdateMovieController implements UpdatedMovieController
{
  @Autowired
  private MovieIndexService movieIndexService;
  
  @Override
  public void updateMovieByID(String title, int id){
    log.debug("Movie ID to Update {} with following title {}", id, title);
    movieIndexService.updateMovieByID(title, id);
  }
}
