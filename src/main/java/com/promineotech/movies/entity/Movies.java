/**
 * 
 */
package com.promineotech.movies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movies {
  private Long moviePK;
  private String title;
  private String runtime;
  private String release_date;
  private String genre;
  private String director;
  
  //@JsonIgnore
  public Long getMoviePK() {
    return moviePK;
   

  }

}
