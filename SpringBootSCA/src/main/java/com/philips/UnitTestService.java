/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
@Service
public class UnitTestService {
  public String runCommand(String[] command) throws IOException, InterruptedException {
    final ProcessBuilder pb1 = new ProcessBuilder(command);
    Process process;
    process = pb1.start();
    process.waitFor();
    final BufferedReader out = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String s = out.readLine();
    while (s!= null && s.charAt(0) != 'T') {
      s = out.readLine();
    }
    out.close();
    return s;
  }

  public double parseString(String s) throws IOException {
    double time=0.0;
    if(s!=null) {
      time = Double.parseDouble((String) s.subSequence(6, s.length()));
    }
    return time * 1000;
  }
}
