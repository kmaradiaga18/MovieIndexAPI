/**
 * 
 */
package com.promineotech.movies.entity;

import lombok.Data;

@Data
public class NewMovieRequest {
  private String title;
  private String runtime;
  private String release_date; 
  private String genre_id;
  private String director_id;
}
