/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import org.springframework.stereotype.Service;

@Service
public class GatingService {
  String nogo="nogo";
  String go = "go";

  public String checkForGate(int currentvalue,int threshold) {
    if(currentvalue < threshold) {
      return go;
    } else {
      return nogo;
    }
  }

  public String coverageGate(int currentvalue,int threshold) {
    if(currentvalue>threshold) {
      return go;
    } else {
      return nogo;
    }
  }

  public String unittestGate(boolean flag) {
    if(flag) {
      return go;
    } else {
      return nogo;
    }
  }

  public String duplicateGate(int duplicate) {
    if(duplicate==1) {
      return nogo;
    } else {
      return go;
    }
  }
}
