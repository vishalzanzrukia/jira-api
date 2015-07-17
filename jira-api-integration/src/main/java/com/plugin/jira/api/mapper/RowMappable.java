package com.plugin.jira.api.mapper;

import org.apache.poi.ss.usermodel.Row;

/**
 * Used to map row with Issue
 * 
 * @author vishal.zanzrukia
 * 
 * @param <T>
 */
public interface RowMappable<T> {
  /**
   * It will map row cells to Issue
   * 
   * @param row
   * @return
   */
  T mapRow(Row row);
}
