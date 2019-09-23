/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import org.junit.Assert;
import org.junit.Test;

public class ResultsTest {

  Results results = new Results();
  @Test
  public void resultsTestProjectName() {
    results.setProjectname("Training");
    final String expected = "Training";
    Assert.assertEquals(expected,results.getProjectname());
  }
  @Test
  public void resultsTestComplexity() {
    results.setCyclomaticcomplexity(2);
    final int expected=2 ;
    Assert.assertEquals(expected,results.getCyclomaticcomplexity());
  }
  @Test
  public void resultsTestWarnings() {
    results.setStaticwarnings(3);
    final int expected=3;
    Assert.assertEquals(expected,results.getStaticwarnings());
  }

  @Test
  public void resultsTestSecurity() {
    results.setSecurityvulnerability(1);
    final int expected=1;
    Assert.assertEquals(expected,results.getSecurityvulnerability());
  }
  @Test
  public void resultsTestCoverage() {
    results.setCodecoverage(60);
    final int expected=60;
    Assert.assertEquals(expected,results.getCodecoverage());
  }


}
