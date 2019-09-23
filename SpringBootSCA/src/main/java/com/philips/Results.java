/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

public class Results {
  String projectname;
  int cyclomaticcomplexity;
  int staticwarnings;
  int securityvulnerability;
  int codecoverage;
  public String getProjectname() {
    return projectname;
  }
  public void setProjectname(String projectname) {
    this.projectname = projectname;
  }
  public int getCyclomaticcomplexity() {
    return cyclomaticcomplexity;
  }
  public void setCyclomaticcomplexity(int cyclomaticcomplexity) {
    this.cyclomaticcomplexity = cyclomaticcomplexity;
  }
  public int getStaticwarnings() {
    return staticwarnings;
  }
  public void setStaticwarnings(int staticwarnings) {
    this.staticwarnings = staticwarnings;
  }
  public int getSecurityvulnerability() {
    return securityvulnerability;
  }
  public void setSecurityvulnerability(int securityvulnerability) {
    this.securityvulnerability = securityvulnerability;
  }
  public int getCodecoverage() {
    return codecoverage;
  }
  public void setCodecoverage(int codecoverage) {
    this.codecoverage = codecoverage;
  }

}
