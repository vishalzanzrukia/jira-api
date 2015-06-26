package com.vishal.jira.api;

import java.util.Set;

import com.vishal.jira.api.domain.Issue;
import com.vishal.jira.api.excel.ExcelToIssueDataExtractor;


/**
 * @author vishal.zanzrukia
 * 
 */
public class ExcelExtractTest {
  public static void main(String[] args) {
    readExcelData(GenericConstants.INPUT_FILE_PATH, 2);
  }

  public static void readExcelData(String filePath, int sheetNo) {
    ExcelToIssueDataExtractor dataExtractor = new ExcelToIssueDataExtractor();
    Set<Issue> issues = dataExtractor.readData(new MyConfig());
    int index = 0;
    
    System.out.println(issues.size());
    for (Issue issue : issues) {
      System.out.println(index++ + "--------------------------------------------------");
      System.out.println("getProjectName :: " + issue.getProjectName());
      System.out.println("getDescription :: " + issue.getDescription());
      System.out.println("getStatus :: " + issue.getStatus());
      System.out.println("getPriority :: " + issue.getPriority());
      System.out.println("getType :: " + issue.getType());
    }
  }
}
