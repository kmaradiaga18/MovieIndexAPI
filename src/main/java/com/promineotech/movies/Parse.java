package com.promineotech.movies;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.csv.*;


public class Parse {

  
  public static void main(String[] args) throws IOException {
    
    Reader in;
    try {
      in = new FileReader("./src/main/resources/film_registry.csv");
      
      Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
      HashSet<String> genreSet = new HashSet<String>();
      HashSet<String> directorSet = new HashSet<String>();
      HashSet<String> bridgeSet = new HashSet<String>();
      
      for (CSVRecord record : records) {
          //Headers: Position,Title,Title Type,Runtime,Year,Genres,Directors
          String titleName = record.get("Title");
          //String position = record.get("Position");  
          String runTime = record.get("Runtime");  
          String releaseDate = record.get("Year");  
          String genre = record.get("Genres");  
          String directorName = record.get("Directors");
          
          //INSERT INTO movies.movie (movie_id, title, runtime, release_date, director_id, genre_id) 
          //SELECT NULL, 'Fantasia', 124, 1940, 
          //director_id FROM director WHERE director_name = 'Samuel Armstrong',
          //genre_id FROM genre WHERE genre_category = 'Animation';
          
          //System.out.println("INSERT INTO movies (movie_id, title, runtime, release_date, director_id, genre_id) "
          //    + "SELECT NULL, '" + titleName + "'," + runTime + ", director_id, " + yearReleased +" FROM directors WHERE director_name = 'Director Name';");

//           System.out.println(String.format("INSERT INTO movies.movie (movie_id, title, runtime, release_date, director_id, genre_id) "
//               + "VALUES (NULL, '%s', %s, %s, "
//               + "(SELECT director_id FROM director WHERE director_name = '%s'),"
//               + "(SELECT genre_id FROM genre WHERE genre_category = '%s'));"
//               , titleName.replace("'",""), runTime, releaseDate, directorName.split(",")[0].replace("'", "").trim(), genre.split(",")[0].replace("-","_").trim()));

          bridgeSet.add(String.format("INSERT INTO movies.bridge(director_id, genre_id) "
          + "VALUES ((SELECT director_id FROM director where director_name = '%s'),"
          + "(SELECT genre_id FROM genre WHERE genre_category = '%s'));"
          , directorName.split(",")[0].replace("'", "").trim(), genre.split(",")[0].replace("-","_").trim()));
          
          List<String> genreList = Arrays.asList(genre.split(","));
          for(int i=0; i < genreList.size(); i++) {
            genreSet.add(genreList.get(i).replace("-","_").trim()); // trimming to remove white space from initial data. Caused errors when pulling info (caused duplicates)
          }
          
          List<String> directorList = Arrays.asList(directorName.split(","));
          for(int i=0; i <directorList.size(); i++) {
            // Directors with multiple ' broke the INSERT sql statement.
            // ex: Harry d'Abbadie d'Arrast, so we replaced ' with blanks
            directorSet.add(directorList.get(i).replace("'", "").trim());
          }
      }
      
      for(String value : genreSet) {
        //System.out.println("INSERT INTO movies.genre VALUES (NULL, '" + value.trim() + "');");
      }
      
      for(String director : directorSet) {
        //System.out.println("INSERT INTO movies.director VALUES (NULL, '" + director.trim() + "');");
      }

      for(String link : bridgeSet) {
        System.out.println(link);
      }
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}