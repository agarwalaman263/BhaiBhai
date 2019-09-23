/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaticToolService {
  @Autowired
  StaticToolDaoImpl staticToolDaoImpl;

  public void insert(String projectname) {
    staticToolDaoImpl.insert(projectname);
  }

  public boolean checkforrow(String projectname) {
    return staticToolDaoImpl.getallrows(projectname);
  }

  public Results getvalue(String projectname) {
    return staticToolDaoImpl.get(projectname);
  }

  public void updatesecurity(String projectname, int value) {
    staticToolDaoImpl.updatesecurity(projectname, value);
  }

  public void updatecomplexity(String projectname, int value) {
    staticToolDaoImpl.updatecomplexity(projectname, value);
  }

  public void updatewarnings(String projectname, int value) {
    staticToolDaoImpl.updatewarnings(projectname, value);
  }

  public void updatecoverage(String projectname, int value) {
    staticToolDaoImpl.updatecoverage(projectname, value);
  }

  public void addRowIfNotExists(String projectname) {
    if (!checkforrow(projectname)) { insert(projectname); }
  }
  public int runCommandLineArgument(String[] command, String binPath) throws InterruptedException {
    final ProcessBuilder pb = new ProcessBuilder(command);
    pb.directory(new File(binPath));
    Process process;
    try {
      process = pb.start();
    } catch (final IOException e1) {
      return 1;
    }
    process.waitFor();
    return process.exitValue();
  }

  public void addToResults(String pattern, File file, List<String> result) {
    if (file.isFile() && file.getName().matches(pattern)) {
      result.add(file.getAbsolutePath());
    }
  }

  public void searchFilesInSubDirectory(final String pattern, final File folder, List<String> result) {
    for (final File file : folder.listFiles()) {
      if (file.isDirectory()) {
        searchFilesInSubDirectory(pattern, file, result);
      }
      addToResults(pattern, file, result);
    }
  }

  public List<String> getAllClassesWithPackage(List<String> allTests) {
    final List<String> results = new ArrayList<>();
    for (final String testFile : allTests) {
      final String[] paths = testFile.split("\\\\");
      final List<String> list = Arrays.asList(paths);
      final int index = list.indexOf("test-classes");
      final StringBuilder bld = new StringBuilder();
      for (int i = index + 1; i < paths.length - 1; i++) {
        bld.append(paths[i] + ".");
      }
      paths[paths.length - 1] = paths[paths.length - 1].replace(".class", "");
      bld.append(paths[paths.length - 1]);
      results.add(bld.toString());
    }
    return results;

  }
  public String propertiesFileReader(String key,String currentdir) {
    try (InputStream inStream = new FileInputStream(currentdir+"//src//main//resources//application.properties")) {

      final Properties prop = new Properties();
      prop.load(inStream);
      return prop.getProperty(key);

    } catch (final IOException io) {
      return null;
    }
  }

  public int compare(String userconfig,String defaultValue) {
    if("1000".equals(userconfig)) {
      return Integer.parseInt(defaultValue);
    } else {
      return Integer.parseInt(userconfig);
    }
  }


}
