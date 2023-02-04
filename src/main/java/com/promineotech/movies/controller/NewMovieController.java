package com.promineotech.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.promineotech.movies.entity.Movies;

@RestController
@RequestMapping("/movies")

public interface NewMovieController {
  @PostMapping("/create")
  @Operation(summary = "Create a new movie for index",
  description = "Returns a new movie for the index once all parameters have been input",
   responses = {
      @ApiResponse(
      responseCode = "201", 
      description = "Successful movie created", 
      content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = Movies.class))),
      @ApiResponse(
        responseCode = "500", 
        description = "Unplanned Error Occured")
    }
  )
  @ResponseStatus(code = HttpStatus.CREATED)
  int createNewMovie(@RequestBody Movies newMovieRequest);

  // Example for Create JSON in Swagger
  /*
{
  "title": "Test Title",
  "runtime": "124",
  "release_date": "1994",
  "genre_id": "Adventure",
  "director_id": "Victor Fleming"
}
*/

  @DeleteMapping("/movie/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  @Operation(summary = "Create a new movie for index",
  description = "Returns a new movie for the index once all parameters have been input",
   responses = {
      @ApiResponse(
        responseCode = "200", 
        description = "Successful removal of movie"),
      @ApiResponse(
        responseCode = "500", 
        description = "Unplanned Error Occured")
    }
  )

  void deleteMovieByID(@PathVariable("id") int id);
}