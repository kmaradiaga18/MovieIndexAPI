/**
 * 
 */
package com.promineotech.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RestController
@RequestMapping("/movies")
public interface UpdatedMovieController {

  @Validated
  @PutMapping("/update")
  @ResponseStatus(code = HttpStatus.OK)
  @Server(url = "http://localhost:8080", description = "Local server.")

    @Operation(summary = "Update an exisitng movie in index",
               description = "Returns an exisitng movie with new parameters",
                responses = {
            @ApiResponse(
            responseCode = "200", 
            description = "Movie successfully updated"),
            @ApiResponse(
                responseCode = "400", 
                description = "Data entered was invalid")
    })
   
   
    void updateMovieByID(@Parameter(description = "The movie title") @RequestParam(required = true) String title,
                         @Parameter(description = "The movie id") @RequestParam(required = true) int id);
}
  
