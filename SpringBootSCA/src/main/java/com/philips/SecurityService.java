/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Service;
@Service
public class SecurityService {
  public int parseTextFile(String currentdir,String projectname) throws IOException {

    final File f = new File(currentdir + "\\securityreport\\" + projectname + ".txt");
    final FileReader fr = new FileReader(f);
    final BufferedReader br = new BufferedReader(fr);
    String line;
    int count = 0;
    while ((line = br.readLine()) != null) {
      final String[] words = line.split(" ");
      if (words[0].equals("Line:")) {
        count++;
      }
    }
    fr.close();
    br.close();
    return count;
  }
}
