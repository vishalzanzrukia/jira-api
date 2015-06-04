package com.vishal.jira.api;

public class GenericConstants {

  public enum PRIORITY {
    IMPORTANT("Important"), VERY_HIGH("VeryHigh");

    private String value;

    private PRIORITY(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }
  }

  public static final String INPUT_FILE_PATH =
      "D:\\me\\projects\\fresca\\Timesheets\\DailyStatus\\Daily_Status_May_2015_test.xlsx";
  public static final int INPUT_SHEET_NO = 1;
}
