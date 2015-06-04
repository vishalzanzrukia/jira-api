package com.vishal.jira.api.pojo;

import org.apache.poi.ss.usermodel.Cell;

/**
 * It will contain all the fields related to Issue.
 * 
 * @author vishal.zanzrukia
 * 
 */
public class Issue {

  private Cell issueId;
  private Cell description;
  private Cell type;
  private Cell status;
  private Cell priority;
  private Cell projectName;


  /**
   * @param projectName
   */
  public Issue(Cell projectName) {
    this.projectName = projectName;
  }

  /**
   * @param issueId
   */
  public void setIssueId(Cell issueId) {
    this.issueId = issueId;
  }



  /**
   * @return
   */
  public String getIssueId() {
    if (issueId != null) {
      return issueId.getStringCellValue();
    }
    return null;
  }

  /**
   * @return
   */
  public String getDescription() {
    if (description != null) {
      return description.getStringCellValue();
    }
    return null;
  }

  /**
   * @param description
   */
  public void setDescription(Cell description) {
    this.description = description;
  }

  /**
   * @return
   */
  public Cell getType() {
    return type;
  }

  /**
   * @param type
   */
  public void setType(Cell type) {
    this.type = type;
  }

  /**
   * @return
   */
  public String getStatus() {
    if (status != null) {
      return status.getStringCellValue();
    }
    return null;
  }

  /**
   * @param status
   */
  public void setStatus(Cell status) {
    this.status = status;
  }

  /**
   * @return
   */
  public String getPriority() {
    if (priority != null) {
      return priority.getStringCellValue();
    }
    return null;
  }

  /**
   * @param priority
   */
  public void setPriority(Cell priority) {
    this.priority = priority;
  }

  /**
   * @return
   */
  public String getProjectName() {
    if (projectName != null) {
      return projectName.getStringCellValue();
    }
    return null;
  }
}
