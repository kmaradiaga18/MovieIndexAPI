package com.promineotech.movies.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;


@RestController
@RequestMapping("/movies")

public interface MovieIndexController {

  @Operation(summary = "Get movies",
             description = "Returns a list of movies based on the optional genre and/or director parameters.")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200", 
          description = "Successful retrieval of movie list"),
      @ApiResponse(
          responseCode = "404", 
          description = "No movies found with the specified genre and/or director"),
      @ApiResponse(
          responseCode = "500", description = "Internal server error")
    }
  
  
  
  )
 
  @ResponseStatus(code = HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  List<Movies> getMovies(@Parameter(description = "The movie genre") @RequestParam(required = false) MovieGenre genre,
                        @Parameter(description = "The movie director") @RequestParam(required = false) String director);
     
  
}