/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.philips.Standardclass;
public class AppTest {
  Standardclass app=new Standardclass();
  @Test
  public void testHelloWorld() {
    final String expected="helloworld";
    assertEquals(expected,app.sayHelloWorld());
  }


}