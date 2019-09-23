/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import org.springframework.stereotype.Component;

@Component
public class ToolResult {
  int value;
  String result;
  public int getValue() {
    return value;
  }
  public void setValue(int value) {
    this.value = value;
  }
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }

}
