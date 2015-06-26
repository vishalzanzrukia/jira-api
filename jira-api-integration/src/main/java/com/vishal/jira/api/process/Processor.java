package com.vishal.jira.api.process;

import com.vishal.jira.api.excel.ExcelConfig;

/**
 * @author Vishal.Zanzrukia
 * 
 * @param <T>
 */
public interface Processor<T> {

  /**
   * @param object
   */
  void process(T object, ExcelConfig config);

}
