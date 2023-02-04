/**
 * 
 */
package com.promineotech.movies.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.promineotech.movies.controller.support.FetchMovieTestSupport;
import com.promineotech.movies.entity.MovieGenre;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

// Removed since we're not using H2 for the local Database.
// We can just make live changes and tests against main DB for now

//@ActiveProfiles("test")
//@Sql(scripts = {
//    "classpath:movie_directors.sql",
//    "classpath:movie_genres.sql", 
//    "classpath:movie_movies.sql"}, 
//  config = @SqlConfig(encoding = "utf-8"))

class FetchMovieTest extends FetchMovieTestSupport {

  @Test
  public void testGetMovie() {
    RestTemplate restTemplate = new RestTemplate();
    MovieGenre genre = MovieGenre.Adventure;
    String director = "Frank%20Capra";
    String uri = getBaseUriForMovies();

    ResponseEntity<Object> response = restTemplate.exchange
        (uri, HttpMethod.GET, null, Object.class);
            
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
  
  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;
}
