/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Service;
import com.opencsv.CSVReader;
@Service
public class CodeCoverageService {
  public int parseCsvFile(String file) throws IOException {
    final FileReader filereader= new FileReader(file);
    final CSVReader csvReader = new CSVReader(filereader);
    double codecoverage = 0.0;
    String[] nextRecord = csvReader.readNext();
    int instructionscovered=0;
    int instructionsmissed=0;
    while((nextRecord = csvReader.readNext()) != null) {
      instructionscovered+= Integer.parseInt(nextRecord[4]);
      instructionsmissed+= Integer.parseInt(nextRecord[3]);
    }
    if((instructionscovered + instructionsmissed)>0) {
      codecoverage = (double) instructionscovered / (instructionscovered + instructionsmissed);
    }
    filereader.close();
    csvReader.close();
    return (int)(codecoverage * 100);
  }
}
