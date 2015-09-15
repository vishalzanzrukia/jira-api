
package com.plugin.jira.api.temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

/**
 * NamedParamPreparedStatement is wrapper class of <code>PreparedStatement</code> which works same
 * like
 * 
 * @author Vishal Zanzrukia
 * @see PreparedStatement
 * 
 */
public class NamedParamPreparedStatement {
  private static final Logger log = Logger.getLogger(NamedParamPreparedStatement.class);

  private final PreparedStatement prepStmt;
  private List<String> fields = new ArrayList<String>();

  public NamedParamPreparedStatement(Connection conn, String sql) throws SQLException {
    int pos;
    while ((pos = sql.indexOf(":")) != -1) {
      int end = sql.substring(pos).indexOf(" ");
      if (end == -1)
        end = sql.length();
      else
        end += pos;
      fields.add(sql.substring(pos + 1, end));
      sql = sql.substring(0, pos) + "?" + sql.substring(end);
    }

    log.debug("Final query : " + sql);
    prepStmt = conn.prepareStatement(sql);
  }

  /**
   * @return
   */
  public PreparedStatement getPreparedStatement() {
    return prepStmt;
  }

  /**
   * @return
   * @throws SQLException
   */
  public ResultSet executeQuery() throws SQLException {
    return prepStmt.executeQuery();
  }

  /**
   * @throws SQLException
   */
  public void close() throws SQLException {
    prepStmt.close();
  }

  /**
   * @param name
   * @param value
   * @throws SQLException
   */
  public void setInt(String name, int value) throws SQLException {
    prepStmt.setInt(getIndex(name), value);
  }

  /**
   * @param name
   * @param value
   * @throws SQLException
   */
  public void setLong(String name, long value) throws SQLException {
    prepStmt.setLong(getIndex(name), value);
  }

  /**
   * @param name
   * @param value
   * @throws SQLException
   */
  public void setDouble(String name, double value) throws SQLException {
    prepStmt.setDouble(getIndex(name), value);
  }

  /**
   * @param name
   * @param value
   * @throws SQLException
   */
  public void setFloat(String name, float value) throws SQLException {
    prepStmt.setFloat(getIndex(name), value);
  }

  /**
   * @param name
   * @param value
   * @throws SQLException
   */
  public void setString(String name, String value) throws SQLException {
    prepStmt.setString(getIndex(name), value);
  }

  /**
   * @param name
   * @return
   */
  private int getIndex(String name) {
    return fields.indexOf(name) + 1;
  }
}
