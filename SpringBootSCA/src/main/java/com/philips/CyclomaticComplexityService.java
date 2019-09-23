/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;
@Service
public class CyclomaticComplexityService {

  int maxComplexity=0;
  public void extractTextDetails() throws IOException {
    final File file =
        new File(Commands.currentdir + "\\complexityreport\\" + Commands.projectname + ".txt");
    final BufferedReader br=new BufferedReader(new FileReader(file));
    try {
      final Map<String, Integer> functionMap = new HashMap<>();
      String st;
      while ((st = br.readLine()) != null) {
        final String[] splitCommand = st.split(",");
        putTextFileOutput(splitCommand, functionMap);
      }
      br.close();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  public int getMaxComplexity() {
    return maxComplexity;
  }

  public Map<String, Integer> putTextFileOutput(String[] string, Map<String, Integer> map) {

    int index = 2;
    while (index < string.length) {
      map.put(string[index], Integer.parseInt(string[index + 1]));
      index = index + 4;
    }
    maxComplexity = calculateMaxComplexity(map);
    return map;
  }

  public int calculateMaxComplexity(Map<String, Integer> map) {
    final Entry<String, Integer> maxEntry =
        Collections.max(map.entrySet(), (Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1
            .getValue().compareTo(e2.getValue()));
    return maxEntry.getValue();
  }
}
