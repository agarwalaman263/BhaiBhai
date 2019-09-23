/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class StaticToolController {

  @Autowired
  StaticToolService service;
  @Autowired
  CodeCoverageService codeCoverageService;
  @Autowired
  CyclomaticComplexityService complexityService;
  @Autowired
  UnitTestService unitTestService;
  @Autowired
  SecurityService securityService;
  @Autowired
  GatingService gatingService;
  @Autowired
  StaticWarningsService staticWarningsService;

  @Autowired
  ToolResult toolResult;
  List<String> classnames;

  @GetMapping("/enter")
  public String homepage() {
    return "home";
  }

  @GetMapping("/start")
  public String getUserDetails(@RequestParam("v1") String projectname,@RequestParam("v2") String projectdir) throws InterruptedException {

    Commands.setProjectname(projectname);
    Commands.setProjectdir(projectdir);
    Commands.setCurrentdir(System.getProperty("user.dir"));

    final File pomfile = new File(projectdir + "\\pom.xml");
    if(!pomfile.exists()) {
      System.out.println("Give the maven Project as Input");
      return "invalidinput";
    }
    final int returnvalue=0;

    /*
     * returnvalue=service.runCommandLineArgument(Commands.mavenclean, Commands.projectdir);
     * returnvalue=service.runCommandLineArgument(Commands.mavencompile, Commands.projectdir);
     * returnvalue=service.runCommandLineArgument(Commands.maventestcompile, Commands.projectdir);
     * returnvalue=service.runCommandLineArgument(Commands.maveninstall, Commands.projectdir);
     */
    if (returnvalue == 1) {
      System.out.println("Build Failure on the given project");
      return "invalidinput";
    }
    final List<String> resultFiles = new ArrayList<>();
    service.searchFilesInSubDirectory(".*\\.class",new File(projectdir + "/target/test-classes"), resultFiles);
    classnames = service.getAllClassesWithPackage(resultFiles);
    service.addRowIfNotExists(projectname);
    return "choosewithconfig";
  }

  @GetMapping("/coverage")
  public ToolResult codecoverage(@RequestParam("item0") String userconfig) throws IOException, InterruptedException {
    final int threshold=service.compare(userconfig,service.propertiesFileReader("coveragethreshold",Commands.currentdir));
    for (final String classname : classnames) {
      service.runCommandLineArgument(Commands.getjavaagent(classname), Commands.projectdir);
    }
    service.runCommandLineArgument(Commands.getjavacommand(), Commands.projectdir);
    final int codecoverage = codeCoverageService.parseCsvFile(Commands.currentdir + "\\jacoco-report\\" + Commands.projectname + ".csv");
    /*
     * final Results result = service.getvalue(Commands.projectname); final int prevresult =
     * result.getCodecoverage();
     */service.updatecoverage(Commands.projectname, codecoverage);
     System.out.println(codecoverage);
     toolResult.setValue(codecoverage);
     toolResult.setResult(gatingService.coverageGate(codecoverage, threshold));
     return toolResult;
  }

  @GetMapping("/test")
  public ToolResult unitTestTime(@RequestParam("item2") String userconfig,Model model) throws IOException, InterruptedException {
    final int threshold=service.compare(userconfig,service.propertiesFileReader("unittesttimethreshold",Commands.currentdir));
    boolean flag = true;
    double maxtime=0.0;
    for (final String classname : classnames) {
      System.out.println(classname);
      final String s = unitTestService.runCommand(Commands.gettestcommand(classname));
      final double time=unitTestService.parseString(s);
      maxtime=Math.max(maxtime,time);
      System.out.println(time);
    }
    if(maxtime>threshold) {
      flag=false;
    }
    toolResult.setValue((int)maxtime);
    toolResult.setResult(gatingService.unittestGate(flag));
    return toolResult;
  }

  @GetMapping("/security")
  public ToolResult securityVulnerabilities(@RequestParam("item4") String userconfig) throws IOException, InterruptedException {
    final int threshold=service.compare(userconfig,service.propertiesFileReader("securityvulnerabilitythreshold",Commands.currentdir));
    service.runCommandLineArgument(Commands.getsecuritycommand(), Commands.getvcgpath());
    final int vulnerabilitiescount = securityService.parseTextFile(Commands.currentdir, Commands.projectname);
    /*
     * final Results result = service.getvalue(Commands.projectname); final int previouscount =
     * result.getSecurityvulnerability();
     */
    System.out.println(vulnerabilitiescount);
    service.updatesecurity(Commands.projectname, vulnerabilitiescount);

    toolResult.setValue(vulnerabilitiescount);
    toolResult.setResult(gatingService.checkForGate(vulnerabilitiescount, threshold));
    return toolResult;
  }

  @GetMapping("/duplicate")
  public ToolResult duplicates(@RequestParam("item5") String userconfig) throws InterruptedException {
    final int threshold=service.compare(userconfig,service.propertiesFileReader("duplicatethreshold",Commands.currentdir));
    final int duplicate =service.runCommandLineArgument(Commands.getduplicatecommand(threshold), Commands.getsimianpath());
    System.out.println(duplicate);
    toolResult.setValue(duplicate);
    toolResult.setResult(gatingService.duplicateGate(duplicate));
    return toolResult;
  }

  @GetMapping("/warnings")
  public ToolResult staticWarnings(@RequestParam("item1") String userconfig) throws Exception {
    final int threshold=service.compare(userconfig,service.propertiesFileReader("staticwarningsthreshold",Commands.currentdir));
    service.runCommandLineArgument(Commands.getPmdCommand(), Commands.getpmdbinpath());
    final int staticwarningscount = staticWarningsService.parseXML(Commands.projectname, Commands.currentdir);
    System.out.println(staticwarningscount);
    /*
     * final Results result = service.getvalue(Commands.projectname);
     * final int prevcount = result.getStaticwarnings();
     *
     */
    service.updatewarnings(Commands.projectname, staticwarningscount);
    toolResult.setValue(staticwarningscount);
    toolResult.setResult(gatingService.checkForGate(staticwarningscount, threshold));
    return toolResult;
  }

  @GetMapping("/complexity")
  public ToolResult getComplexity(@RequestParam("item3") String userconfig) throws IOException, InterruptedException {
    final int threshold=service.compare(userconfig,service.propertiesFileReader("cyclomaticcomplexitythreshold",Commands.currentdir));
    int maxcomplexity = 0;
    service.runCommandLineArgument(Commands.getcyviscommand(), Commands.currentdir+"\\files\\cyvis-0.9");
    complexityService.extractTextDetails();
    maxcomplexity = Math.max(complexityService.getMaxComplexity(), maxcomplexity);
    service.updatecomplexity(Commands.projectname, maxcomplexity);

    toolResult.setValue(maxcomplexity);
    toolResult.setResult(gatingService.checkForGate(maxcomplexity, threshold));
    System.out.println(maxcomplexity);
    return toolResult;
  }

}
