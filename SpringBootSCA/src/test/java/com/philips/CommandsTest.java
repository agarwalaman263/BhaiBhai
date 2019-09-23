/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import org.junit.Assert;
import org.junit.Test;

public class CommandsTest {
  Commands commands=new Commands();

  @Test
  public void projectdirTest() {
    Commands.setProjectdir("C:\\Users");
    final String expected="C:\\Users";
    Assert.assertEquals(expected,Commands.projectdir);
  }

  @Test
  public void projectnameTest() {
    Commands.setProjectname("ExampleWebApp");
    final String expected="ExampleWebApp";
    Assert.assertEquals(expected,Commands.projectname);
  }

  @Test
  public void currentdirTest() {
    Commands.setCurrentdir("C:\\Users");
    final String expected="C:\\Users";
    Assert.assertEquals(expected,Commands.currentdir);
  }

  @Test
  public void pmdBinPathTest() {
    Commands.setCurrentdir("C:\\Users");
    final String expected="C:\\Users\\files\\pmd-bin-6.16.0\\pmd-bin-6.16.0\\bin";
    Assert.assertEquals(expected,Commands.getpmdbinpath());
  }

  @Test
  public void vcgBinPathTest() {
    Commands.setCurrentdir("C:\\Users");
    final String expected="C:\\Users\\files\\VisualCodeGrepper";
    Assert.assertEquals(expected,Commands.getvcgpath());
  }

  @Test
  public void getsimianpathTest() {
    Commands.setCurrentdir("C:\\Users");
    final String expected="C:\\Users\\files\\simian\\bin";
    Assert.assertEquals(expected,Commands.getsimianpath());
  }

  @Test
  public void getsecuritycommandTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String[] expected=new String[] {"cmd", "/c", "VisualCodeGrepper.exe", "-c", "-l", "java",
        "-t","C:\\Users","--results",
        "C:\\Users"+"\\securityreport\\"+"ExampleWebApp"+".txt"};
    Assert.assertArrayEquals(expected,Commands.getsecuritycommand());
  }

  @Test
  public void getduplicatecommandTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String simianpath="C:\\Users\\files\\simian\\bin";
    final String[] expected=new String[] {"cmd", "/c", "java", "-jar", simianpath+"\\simian-2.5.10.jar",
        "C:\\Users" +"\\**\\*.java","-threshold=6","-formatter=plain",">",
        "C:\\Users"+"\\duplicatereport\\"+"ExampleWebApp"+".txt"};
    Assert.assertArrayEquals(expected,Commands.getduplicatecommand(6));
  }

  @Test
  public void getjavaagentTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String[] expected=new String []{"cmd", "/c", "java", "-cp",
        Commands.currentdir+"\\files\\junit-4.12.jar;"+Commands.currentdir+"\\files\\hamcrest-core-1.3.jar;"+Commands.projectdir+"\\target\\test-classes;"+Commands.projectdir+"\\target\\classes",
        "-javaagent:"+Commands.currentdir+"\\files\\jacocoagent.jar=destfile="+Commands.currentdir+"\\jacoco-report\\jacoco-"+Commands.projectname+".exec",
        "org.junit.runner.JUnitCore", "com.philips"};
    Assert.assertArrayEquals(expected,Commands.getjavaagent("com.philips"));
  }

  @Test
  public void getjavacommandTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String[] expected=new String []{"cmd", "/c","start","java", "-jar",
        Commands.currentdir+"\\files\\jacococli.jar", "report",
        Commands.currentdir+"\\jacoco-report\\jacoco-"+Commands.projectname+".exec", "--classfiles",
        Commands.projectdir+"\\target\\classes","--classfiles",
        Commands.projectdir+"\\target\\test-classes", "--sourcefiles",
        Commands.projectdir+"\\src", "--sourcefiles" ,Commands.projectdir+"\\target" ,"--csv",
        Commands.currentdir+"\\jacoco-report\\"+Commands.projectname+".csv"};
    Assert.assertArrayEquals(expected,Commands.getjavacommand());
  }

  @Test
  public void gettestcommandTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String[] expected=new String[] {"cmd","/c","java", "-cp",
        Commands.currentdir+"\\files\\junit-4.12.jar;"+Commands.currentdir+"\\files\\hamcrest-core-1.3.jar;"+Commands.projectdir+"\\target\\test-classes;"+Commands.projectdir+"\\target\\classes",
        "org.junit.runner.JUnitCore", "com.philips"};
    Assert.assertArrayEquals(expected,Commands.gettestcommand("com.philips"));
  }

  @Test
  public void getPmdCommandTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String[] expected=new String[] { "cmd", "/c", "pmd", "-d", Commands.projectdir, "-f", "xml", "-R", "rulesets/java/quickstart.xml",
        ">", Commands.currentdir+"\\Pmdreports\\" + Commands.projectname + ".xml" };
    Assert.assertArrayEquals(expected,Commands.getPmdCommand());
  }

  @Test
  public void getcyviscommandTest() {
    Commands.setCurrentdir("C:\\Users");
    Commands.setProjectdir("C:\\Users");
    Commands.setProjectname("ExampleWebApp");
    final String[] expected=new String[] { "cmd", "/c", "cd", Commands.currentdir+"\\files\\cyvis-0.9", "&&", "jar", "cf", "jar1.jar",
        Commands.projectdir, "&&", "java", "-jar",
        "cyvis-0.9.jar", "-p", "jar1.jar", "-t", Commands.currentdir+"\\complexityreport\\"+Commands.projectname+".txt" };
    Assert.assertArrayEquals(expected,Commands.getcyviscommand());
  }

}
