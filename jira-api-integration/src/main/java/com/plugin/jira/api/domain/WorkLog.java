package com.plugin.jira.api.domain;

import java.util.Date;

/**
 * @author Vishal.Zanzrukia
 * 
 */
public class WorkLog extends BaseDomian {

  private String id;
  private String timeSpent;
  private Date logTime;
  private String comment;

  /**
   * @return the timeSpent
   */
  public String getTimeSpent() {
    return timeSpent;
  }

  /**
   * @param timeSpent the timeSpent to set
   */
  public void setTimeSpent(String timeSpent) {
    this.timeSpent = timeSpent;
  }

  /**
   * @return the logTime
   */
  public Date getLogTime() {
    return logTime;
  }

  /**
   * @param logTime the logTime to set
   */
  public void setLogTime(Date logTime) {
    this.logTime = logTime;
  }

  /**
   * @return the comment
   */
  public String getComment() {
    return comment;
  }

  /**
   * @param comment the comment to set
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    WorkLog other = (WorkLog) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }


}
