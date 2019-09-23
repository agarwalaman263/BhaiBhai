/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import org.junit.Assert;
import org.junit.Test;

public class GatingServiceTest {

  GatingService gatingService=new GatingService();
  @Test
  public void checkForGateTestGo() {
    final String expected="go";
    Assert.assertEquals(expected,gatingService.checkForGate(5, 6));
  }

  @Test
  public void checkForGateTestNoGo() {
    final String expected="nogo";
    Assert.assertEquals(expected,gatingService.checkForGate(5, 4));
  }

  @Test
  public void coverageGateTestGo() {
    final String expected="go";
    Assert.assertEquals(expected,gatingService.coverageGate(5,4));
  }

  @Test
  public void coverageGateTestNoGo() {
    final String expected="nogo";
    Assert.assertEquals(expected,gatingService.coverageGate(5,6));
  }

  @Test
  public void unittestGateTestGo() {
    final String expected="go";
    Assert.assertEquals(expected,gatingService.unittestGate(true));
  }

  @Test
  public void unittestGateTestNoGo() {
    final String expected="nogo";
    Assert.assertEquals(expected,gatingService.unittestGate(false));
  }

  @Test
  public void duplicateGateTestGo() {
    final String expected="go";
    Assert.assertEquals(expected,gatingService.duplicateGate(0));
  }

  @Test
  public void duplicateGateTestNoGo() {
    final String expected="nogo";
    Assert.assertEquals(expected,gatingService.duplicateGate(1));
  }

}
