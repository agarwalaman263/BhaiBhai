/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ResultsMapper implements RowMapper<Results>{

  @Override
  public Results mapRow(ResultSet rs, int rowNum) throws SQLException {
    final Results result=new Results();
    result.setProjectname(rs.getString(1));
    result.setCyclomaticcomplexity(rs.getInt(2));
    result.setStaticwarnings(rs.getInt(3));
    result.setSecurityvulnerability(rs.getInt(4));
    result.setCodecoverage(rs.getInt(5));
    return result;
  }


}
