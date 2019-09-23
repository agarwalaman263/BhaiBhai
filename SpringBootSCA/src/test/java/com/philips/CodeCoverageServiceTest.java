/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

public class CodeCoverageServiceTest {

  CodeCoverageService codeCoverageService=new CodeCoverageService();
  @Test
  public void parseCsvFileTest() throws IOException, InterruptedException {
    final int expected=100;
    assertEquals(expected,codeCoverageService.parseCsvFile(System.getProperty("user.dir") + "\\jacoco-report\\"  + "ExampleWebApp.csv"));
  }


}
