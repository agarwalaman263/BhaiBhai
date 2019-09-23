/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

public interface StaticToolDao {
  void insert(String projectname);

  boolean getallrows(String projectname);

  Results get(String projectname);

  void updatecoverage(String projectname, int value);

  void updatesecurity(String projectname, int value);

  void updatecomplexity(String projectname, int value);

  void updatewarnings(String projectname, int value);
}
