package com.plugin.jira.api.excel;

import java.util.Set;

/**
 * It defines how data extraction can be done.
 * 
 * @author vishal.zanzrukia
 * 
 */
public interface DataExtractor<T> {

  /**
   * It provides set of rows or in our case Issue
   * 
   * @param config
   * @return
   */
  Set<T> readData(ExcelConfig config);
}
