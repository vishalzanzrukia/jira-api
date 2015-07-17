package com.plugin.jira.api.excel;

import org.apache.poi.ss.usermodel.Row;

import com.plugin.jira.api.domain.Issue;
import com.plugin.jira.api.mapper.RowMappable;
import com.plugin.jira.api.mapper.SimpleExcelIssueMapper;


/**
 * It will map column to it's actual purpose.<BR>
 * <STRONG>NOTE:</STRONG> here, CI is Column Index
 * 
 * @author vishal.zanzrukia
 */
public class ExcelColumnIndexMapper {

  private int issueIdCI;
  private int descriptionCI;
  private int summaryCI;
  private int typeCI;
  private int statusCI;
  private int priorityCI;
  private int projectNameCI;

  /**
   * set issue projectName column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param projectNameCI
   */
  public ExcelColumnIndexMapper(int projectNameCI) {
    this.projectNameCI = projectNameCI;
  }

  /**
   * It will provide the API mapper which will map Row to Issue
   * 
   * @return RowMappable
   */
  RowMappable<Issue> getMapper() {
    SimpleExcelIssueMapper<Issue> mapper = new SimpleExcelIssueMapper<Issue>() {
      @Override
      public Issue mapRow(Row row) {
        Issue issue = new Issue(row.getCell(projectNameCI));
        issue.setIssueId(row.getCell(issueIdCI));
        issue.setDescription(row.getCell(descriptionCI));
        issue.setSummary(row.getCell(summaryCI));
        issue.setType(row.getCell(typeCI));
        issue.setStatus(row.getCell(statusCI));
        issue.setPriority(row.getCell(priorityCI));
        return issue;
      }
    };
    return mapper;
  }

  /**
   * set issue status column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param statusCI
   */
  public void setStatusCI(int statusCI) {
    this.statusCI = statusCI;
  }

  /**
   * set issue priority column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param priorityCI
   */
  public void setPriorityCI(int priorityCI) {
    this.priorityCI = priorityCI;
  }

  /**
   * set issue type column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param typeCI
   */
  public void setTypeCI(int typeCI) {
    this.typeCI = typeCI;
  }

  /**
   * set issue description column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param descriptionCI
   */
  public void setDescriptionCI(int descriptionCI) {
    this.descriptionCI = descriptionCI;
  }

  /**
   * set issue summary column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param summaryCI
   */
  public void setSummaryCI(int summaryCI) {
    this.summaryCI = summaryCI;
  }

  /**
   * set issue issue-id column index<BR>
   * <STRONG>NOTE :</STRONG> index starts from <STRONG>0</STRONG>
   * 
   * @param issueIdCI
   */
  public void setIssueIdCI(int issueIdCI) {
    this.issueIdCI = issueIdCI;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer("[Project Name Column Index : ");
    buffer.append(projectNameCI);
    buffer.append(", Description Column Index : ");
    buffer.append(descriptionCI);
    buffer.append(", Issue Type Column Index : ");
    buffer.append(typeCI);
    buffer.append(", Issue Status Column Index : ");
    buffer.append(statusCI);
    buffer.append(", Issue Priority Column Index : ");
    buffer.append(priorityCI);
    buffer.append("]");
    return buffer.toString();
  }
}
