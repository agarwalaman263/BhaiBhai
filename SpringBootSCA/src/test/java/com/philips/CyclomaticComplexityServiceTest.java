/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;


public class CyclomaticComplexityServiceTest {

  CyclomaticComplexityService complexityService=new CyclomaticComplexityService();
  @Test
  public void putTextFileOutputtest()
  {
    final String s="com.philips.simpleApp,Customer2,toString,1,28,,<init>,1,18,,setPhone,1,7,,"
        + "setCaddr,1,7,,setCname,1,7,,setId,1,7,,<init>,1,6,,getPhone,1,5,,getCaddr"
        + ",1,5,,getCname,1,5,,getId,1,5,";
    final String s1[]=s.split(",");
    final Map<String,Integer> map=new HashMap<>();
    int index = 2;
    while (index < s1.length) {
      map.put(s1[index], Integer.parseInt(s1[index + 1]));
      index = index + 4;
    }
    final Map<String,Integer> checkmap=new HashMap<>();
    Assert.assertEquals(map, complexityService.putTextFileOutput(s1,checkmap));
  }
  @Test
  public void calculateMaxCompexityTest()
  {
    final Map<String,Integer> map=new HashMap<>();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c",5);
    Assert.assertEquals(5, complexityService.calculateMaxComplexity(map));
  }
  /*
   * @Test public void consoleInteractorTest() throws IOException { final String
   * s[]={"cd","C:\\Users\\","&&","echo.>testfile.txt"}; PowerMockito.mockStatic(Commands.class);
   * PowerMockito.when(Commands.getcyviscommand()).thenReturn(s);
   * complexityService.consoleInteractor(); final File testfile=new File("C:\\Users\\testfile.txt");
   * Assert.assertTrue(testfile.exists());
   *
   * }
   */


}
