/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

public class SecurityServiceTest {
  SecurityService securityService=new SecurityService();
  @Test
  public void securityServiceTest() throws IOException {
    final int expected=3;
    assertEquals(expected,securityService.parseTextFile(System.getProperty("user.dir"),"ExampleWebApp"),0.0);
  }
}
