/**
 * 
 */
package com.promineotech.movies.controller.support;


public class CreateNewMovieTestSupport extends BaseTest{

  protected String createNewMovieBody() {
    //@formatter:off
    return  "{\n"
    + "  \"title\":\"Love Me Tonight\",\n"
    + "  \"runtime\":\"104\",\n"
    + "  \"release_date\":\"1932\",\n"
    + "  ]\n"
    + "}";
    //@formatter:on
  }
}
