/**
 * 
 */
package com.promineotech.movies.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UpdateMovieByIDTest {
  @Test
  public void testUpdateMovieWhenNewTiTleIsSupplied() {
    RestTemplate restTemplate = new RestTemplate();
   
    String title = "A%20Whole%20New%20World";  
    String movieId = "12";

    URI uri = URI.create(String.format("http://localhost:%d/movies/update?title=%s&id=%s", serverPort, title, movieId));
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
            
    ResponseEntity<Object> response = restTemplate.exchange
        (uri, HttpMethod.PUT, null, Object.class);
     System.out.println(response.getBody());
     
     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);  
  }
  
  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;
}
