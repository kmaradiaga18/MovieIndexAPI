/**
 * 
 */
package com.promineotech.movies.controller;
import static org.assertj.core.api.Assertions.assertThat;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.movies.controller.support.CreateNewMovieTestSupport;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@Sql(scripts = {
   "classpath:movie_directors.sql",
   "classpath:movie_genres.sql", 
   "classpath:movie_movies.sql"}, 
  config = @SqlConfig(encoding = "utf-8"))

class CreateNewMovie extends CreateNewMovieTestSupport {

  @Test
  void  testCreatesNewMovieSuccess201() {
    //Given: a movie as JSON
     String body = createNewMovieBody();
  
     String uri = getBaseUriForNewMovies();
  
     
     HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.APPLICATION_JSON);
   
     HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
     
     ResponseEntity<Movies> response = getRestTemplate().exchange(uri, HttpMethod.POST, 
       bodyEntity, Movies.class);
     
     assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  
  }
  
  @LocalServerPort
  private int serverPort;
  
}
  