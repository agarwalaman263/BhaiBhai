/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import org.junit.Assert;
import org.junit.Test;

public class StaticWarningsServiceTest {

  StaticWarningsService staticWarningsService=new StaticWarningsService();
  @Test
  public void parseXmlTest() throws Exception
  {
    Assert.assertEquals(4, staticWarningsService.parseXML("ExampleWebApp",System.getProperty("user.dir")));
  }

}
