/**
 * 
 */
package com.promineotech.movies.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import lombok.Getter;

public class BaseTest {

  
  @Autowired
  @Getter 
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
private int serverPort;

  protected String getBaseUriForMovies() {
    return String.format("http://localhost:%d/movies", serverPort);
  }
  
  protected String getBaseUriForNewMovies() {
    
    return String.format("http://localhost:%d/movies?genre=%s&director=%s\", serverPort, genre, director");
  }
}
