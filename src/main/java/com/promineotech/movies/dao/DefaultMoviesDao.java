/**
 * 
 */
package com.promineotech.movies.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.promineotech.movies.entity.MovieGenre;
import com.promineotech.movies.entity.Movies;
import lombok.extern.slf4j.Slf4j;

@Service

@Component
@Slf4j


public class DefaultMoviesDao implements MoviesDao{
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Movies> getMovies(MovieGenre genre, String director) {
    log.debug("DAO: genre={}, director={}", genre, director);

    String sql = "SELECT * FROM movies.movie";
    Map<String, Object> params = new HashMap<>();

    if (genre != null && director != null) {
      sql += " WHERE genre_id IN (SELECT genre_id FROM genre WHERE genre_category = :genre_category) " +
             "AND director_id IN (SELECT director_id FROM director WHERE director_name = :director_name) ";
      params.put("genre_category", genre.toString());
      params.put("director_name", director);
    } else if (genre != null) {
      sql += " WHERE genre_id IN (SELECT genre_id FROM genre WHERE genre_category = :genre_category)";
      params.put("genre_category", genre.toString());
    } else if (director != null) {
      sql += " WHERE director_id IN (SELECT director_id FROM director WHERE director_name = :director_name)";
      params.put("director_name", director);
    }

    return jdbcTemplate.query(sql, params,
        new RowMapper<>(){

          @Override
          public Movies mapRow(ResultSet rs, int rowNum) throws SQLException {
           //formatter:off
            return  Movies.builder()
                .title(rs.getString("title"))
                .runtime(rs.getString("runtime"))
                .release_date(rs.getString("release_date"))
                .genre(rs.getString("genre_id"))
                .director(rs.getString("director_id"))
                .build();
          }});
  }

  @Override
  public int createMovie(String title, String runtime, String release_date, String genre, String director){
    log.debug("DAO: title={}, runtime={}, release_date={}, genre={}, director={}", title, runtime, release_date, genre, director);
    String sql = "INSERT INTO movies.movie (movie_id, title, runtime, release_date, director_id, genre_id) "
               + "VALUES(NULL, :title, :runtime, :release_date, "
               + "(SELECT director_id FROM director WHERE director_name = :director_name),"
               + "(SELECT genre_id FROM genre WHERE genre_category = :genre_category));";

    Map<String, Object> params = new HashMap<>();
    params.put("title", title);
    params.put("runtime", runtime);
    params.put("release_date", release_date);
    params.put("genre_category", genre.toString());
    params.put("director_name", director);

    return jdbcTemplate.update(sql, params);
  }

  @Override
  public int deleteMovieByID(int id){
    log.debug("DAO: movie_id={}", id);
    String sql = "DELETE FROM movies.movie WHERE movie_id = :movie_id";

    Map<String, Object> params = new HashMap<>();
    params.put("movie_id", id);

    return jdbcTemplate.update(sql, params);
  }
  
  @Override
  public int updateMovieByID(String title, int id) {
    log.debug("DAO: title{}, movie_id={}", id);
    String sql = "UPDATE movies.movie SET title = :title WHERE movie_id = :movie_id";
     
    Map<String, Object> params = new HashMap<>();
    params.put("title", title);
    params.put("movie_id", id);
    
    return jdbcTemplate.update(sql, params);
  }
}
