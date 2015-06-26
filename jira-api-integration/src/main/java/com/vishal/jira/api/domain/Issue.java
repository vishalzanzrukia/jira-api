package com.vishal.jira.api.domain;

import org.apache.poi.ss.usermodel.Cell;

/**
 * It will contain all the fields related to Issue.
 * 
 * @author vishal.zanzrukia
 * 
 */
public class Issue {

  private Cell issueId;
  private Cell summary;
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

  /**
   * @return the summary
   */
  public String getSummary() {
    if (summary != null) {
      return summary.getStringCellValue();
    }
    return null;
  }

  /**
   * @param summary the summary to set
   */
  public void setSummary(Cell summary) {
    this.summary = summary;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Issue other = (Issue) obj;
    if (issueId == null) {
      if (other.issueId != null)
        return false;
    } else if (!issueId.equals(other.issueId))
      return false;
    return true;
  }

}
