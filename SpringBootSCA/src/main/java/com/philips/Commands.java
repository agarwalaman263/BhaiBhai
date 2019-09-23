/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

public class Commands {

  //Maven Commands
  protected Commands() {
  }
  protected static final String[] mavenclean = {"cmd", "/c","start","mvn", "clean"};
  protected static final String[] mavencompile = {"cmd", "/c","start", "mvn", "compile"};
  protected static final String[] maventestcompile = {"cmd", "/c","start", "mvn", "test-compile"};
  protected static final String[] maveninstall = {"cmd", "/c","start", "mvn", "install"};

  protected static String projectdir="C:\\";
  protected static String currentdir;
  protected static String projectname;

  public static void setProjectdir(String dir) {
    projectdir=dir;
  }

  public static void setCurrentdir(String dir) {
    currentdir=dir;
  }

  public static void setProjectname(String dir) {
    projectname=dir;
  }

  public static String getpmdbinpath() {
    return currentdir+"\\files\\pmd-bin-6.16.0\\pmd-bin-6.16.0\\bin";
  }

  public static String getvcgpath() {
    return currentdir+"\\files\\VisualCodeGrepper";
  }

  public static String getsimianpath() {
    return currentdir+"\\files\\simian\\bin";
  }


  public static String[] getsecuritycommand() {
    return new String[] {"cmd", "/c", "VisualCodeGrepper.exe", "-c", "-l", "java",
        "-t",projectdir,"--results",
        currentdir+"\\securityreport\\"+projectname+".txt"};
  }

  public static String[] getduplicatecommand(int threshold) {
    return new String[] {"cmd", "/c", "java", "-jar", getsimianpath()+"\\simian-2.5.10.jar",
        projectdir+"\\**\\*.java","-threshold="+threshold,"-formatter=plain",">",
        currentdir+"\\duplicatereport\\"+projectname+".txt"};
  }

  public static String[] getjavaagent(String classname) {
    return new String []{"cmd", "/c", "java", "-cp",
        currentdir+"\\files\\junit-4.12.jar;"+currentdir+"\\files\\hamcrest-core-1.3.jar;"+projectdir+"\\target\\test-classes;"+projectdir+"\\target\\classes",
        "-javaagent:"+currentdir+"\\files\\jacocoagent.jar=destfile="+currentdir+"\\jacoco-report\\jacoco-"+projectname+".exec",
        "org.junit.runner.JUnitCore", classname};
  }

  public static String[] getjavacommand() {
    return new String []{"cmd", "/c","start","java", "-jar",
        currentdir+"\\files\\jacococli.jar", "report",
        currentdir+"\\jacoco-report\\jacoco-"+projectname+".exec", "--classfiles",
        projectdir+"\\target\\classes","--classfiles",
        projectdir+"\\target\\test-classes", "--sourcefiles",
        projectdir+"\\src", "--sourcefiles" ,projectdir+"\\target" ,"--csv",
        currentdir+"\\jacoco-report\\"+projectname+".csv"};
  }

  public static String[] gettestcommand(String classname) {
    return new String[] {"cmd","/c","java", "-cp",
        currentdir+"\\files\\junit-4.12.jar;"+currentdir+"\\files\\hamcrest-core-1.3.jar;"+projectdir+"\\target\\test-classes;"+projectdir+"\\target\\classes",
        "org.junit.runner.JUnitCore", classname};
  }

  public static String[] getPmdCommand() {
    return new String[] { "cmd", "/c", "pmd", "-d", projectdir, "-f", "xml", "-R", "rulesets/java/quickstart.xml",
        ">", currentdir+"\\Pmdreports\\" + projectname + ".xml" };

  }

  public static String[] getcyviscommand() {
    return new String[] { "cmd", "/c", "cd", currentdir+"\\files\\cyvis-0.9", "&&", "jar", "cf", "jar1.jar",
        projectdir, "&&", "java", "-jar",
        "cyvis-0.9.jar", "-p", "jar1.jar", "-t", currentdir+"\\complexityreport\\"+projectname+".txt" };
  }

}
