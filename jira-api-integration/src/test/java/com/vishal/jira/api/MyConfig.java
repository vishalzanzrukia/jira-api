package com.vishal.jira.api;

import com.plugin.jira.api.excel.ExcelColumnIndexMapper;
import com.plugin.jira.api.excel.SimpleExcelConfig;

public class MyConfig extends SimpleExcelConfig {

  public MyConfig() {
    super("Test");
  }

  @Override
  public String getInputFilePath() {
    return GenericConstants.INPUT_FILE_PATH;
  }
  
  @Override
  public ExcelColumnIndexMapper getExcelColumn() {
    ExcelColumnIndexMapper column = new ExcelColumnIndexMapper(0);
    column.setDescriptionCI(1);
    column.setPriorityCI(2);
    column.setTypeCI(3);
    column.setStatusCI(4);
    return column;
  }
}

