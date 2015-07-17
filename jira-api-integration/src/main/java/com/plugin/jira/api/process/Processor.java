package com.plugin.jira.api.process;

import com.plugin.jira.api.excel.ExcelConfig;

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
