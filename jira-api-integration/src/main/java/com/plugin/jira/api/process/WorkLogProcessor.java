package com.plugin.jira.api.process;

import com.plugin.jira.api.domain.WorkLog;
import com.plugin.jira.api.excel.ExcelConfig;
import com.plugin.jira.api.jsongen.JsonGenerator;

/**
 * @author Vishal.Zanzrukia
 * 
 */
public class WorkLogProcessor extends BaseProcessor<WorkLog> {

  public WorkLogProcessor(WorkLog object, JsonGenerator<WorkLog> jsonGenerator, ExcelConfig config) {
    super(object, jsonGenerator, config);
  }

  @Override
  public void process(WorkLog object, ExcelConfig config) {

  }
}
