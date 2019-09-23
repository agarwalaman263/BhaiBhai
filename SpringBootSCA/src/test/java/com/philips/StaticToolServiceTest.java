/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class StaticToolServiceTest {
  StaticToolService staticToolService = new StaticToolService();

  @Test
  public void compareTestIfCondition() {
    final int expected = 10;
    final String userconfig = "1000";
    final String defaultValue = "10";
    Assert.assertEquals(expected, staticToolService.compare(userconfig, defaultValue));
  }

  @Test
  public void compareTestElseCondition() {
    final int expected = 90;
    final String userconfig = "90";
    final String defaultValue = "10";
    Assert.assertEquals(expected, staticToolService.compare(userconfig, defaultValue));
  }

  @Test
  public void propertiesFileReaderTest() {
    final String expected = "3";
    Assert.assertEquals(expected, staticToolService
        .propertiesFileReader("cyclomaticcomplexitythreshold", System.getProperty("user.dir")));
  }

  @Test()
  public void propertiesFileReaderExceptionTest() {
    final String expected = null;
    Assert.assertEquals(expected,
        staticToolService.propertiesFileReader("cyclomaticcomplexitythreshold", "C:\\Users"));
  }

  @Test
  public void staticToolServiceRunCommandTest() throws InterruptedException {
    final int expected = 1;
    final String command[] = {"cmd", "/c", "echo hello"};
    Assert.assertEquals(expected, staticToolService.runCommandLineArgument(command, ""));

  }

  @Test
  public void staticToolServiceRunCommandTest2() throws InterruptedException {
    final int expected = 0;
    final String command[] = {"cmd", "/c", "echo hello"};
    Assert.assertEquals(expected, staticToolService.runCommandLineArgument(command, "C:\\"));

  }

  @Test
  public void addToResultsTest() {
    final List<String> result=new ArrayList<>();
    final int expected=1;
    staticToolService.addToResults(".*\\.csv", new File(System.getProperty("user.dir")+"\\jacoco-report\\ExampleWebApp.csv"), result);
    Assert.assertEquals(expected,result.size());
  }

  @Test
  public void searchFilesInSubDirectoryTest(){
    final List<String> resultFiles=new ArrayList<>();
    staticToolService.searchFilesInSubDirectory(".*\\.class",new File(System.getProperty("user.dir") + "/target/test-classes"), resultFiles);
    Assert.assertFalse(resultFiles.isEmpty());
  }

  @Test
  public void getAllClassesWithPackageTest() {
    final int expected = 3;
    final List<String> list =
        new ArrayList<>(Arrays.asList("C:\\proj1\\test-classes\\com\\philips\\test1.class",
            "C:\\proj2\\test-classes\\com\\philips\\test2.class",
            "C:\\proj3\\test-classes\\com\\philips\\test3.class"));
    final List<String> results = staticToolService.getAllClassesWithPackage(list);
    Assert.assertEquals(expected, results.size());
  }
}
