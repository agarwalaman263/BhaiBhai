/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ResultsMapperTest {

  @Mock
  private ResultSet resultSet;

  ResultsMapper resultMapper = new ResultsMapper();

  @Test
  public void resultMapperTest() throws SQLException {

    Mockito.when(resultSet.getString(1)).thenReturn("Training");
    Mockito.when(resultSet.getInt(2)).thenReturn(2);
    Mockito.when(resultSet.getInt(3)).thenReturn(1);
    Mockito.when(resultSet.getInt(4)).thenReturn(3);
    Mockito.when(resultSet.getInt(5)).thenReturn(60);

    final Results result = resultMapper.mapRow(resultSet,2);
    Assert.assertNotNull(result);
    Assert.assertEquals("Training",result.getProjectname());
    Assert.assertEquals(2,result.getCyclomaticcomplexity());
    Assert.assertEquals(1,result.getStaticwarnings());
    Assert.assertEquals(3,result.getSecurityvulnerability());
    Assert.assertEquals(60,result.getCodecoverage());

  }




}
