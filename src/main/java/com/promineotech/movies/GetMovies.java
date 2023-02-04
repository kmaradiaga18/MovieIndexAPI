/**
 * 
 */
package com.promineotech.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.promineotech.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class GetMovies {

  public static void main(String[] args) {
    SpringApplication.run(GetMovies.class, args);
  }
}
