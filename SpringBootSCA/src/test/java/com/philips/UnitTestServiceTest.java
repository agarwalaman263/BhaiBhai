/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

public class UnitTestServiceTest {
  UnitTestService unitTestService=new UnitTestService();
  @Test
  public void runCommandTest() throws IOException, InterruptedException {
    final String command[]= {"cmd","/c","echo Time: 0.004"};
    final String expected="Time: 0.004";
    assertEquals(expected,unitTestService.runCommand(command));
  }

  @Test
  public void runCommandTestWhile() throws IOException, InterruptedException {
    final String command[]= {"cmd","/c","echo UnitTestTime: 0.004"};
    final String expected=null;
    assertEquals(expected,unitTestService.runCommand(command));
  }

  @Test
  public void parseStringTest() throws IOException, InterruptedException {
    final double expected=4;
    final String s="Time: 0.004";
    assertEquals(expected,unitTestService.parseString(s),0.0);
  }

  @Test
  public void parseStringTestWhile() throws IOException, InterruptedException {
    final double expected=0.0;
    final String s=null;
    assertEquals(expected,unitTestService.parseString(s),0.0);
  }

}
