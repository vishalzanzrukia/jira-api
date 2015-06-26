package com.vishal.jira.api.jsongen;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.vishal.jira.api.domain.Issue;

/**
 * Provide the issue json object
 * 
 * @author Vishal.Zanzrukia
 * 
 */
public class IssueJsonGenerator implements JsonGenerator<Issue> {

  public static final String FIELDS = "fileds";
  public static final String PROJECT = "project";
  public static final String PROJECT_KEY = "key";
  public static final String SUMMARY = "summary";
  public static final String DESCRIPTION = "description";
  public static final String ISSUETYPE = "issuetype";
  public static final String ISSUETYPE_NAME = "name";

  @Override
  public JSONObject generate(Issue bean) throws JSONException {
    JSONObject issueJson = new JSONObject();
    issueJson.put(FIELDS, getFieldJson(bean));
    return issueJson;
  }

  /**
   * @param bean
   * @return
   * @throws JSONException
   */
  private JSONObject getFieldJson(Issue bean) throws JSONException {
    JSONObject json = new JSONObject();
    json.put(DESCRIPTION, bean.getDescription());
    json.put(SUMMARY, bean.getSummary());
    json.put(PROJECT, getProjectJson(bean));
    json.put(ISSUETYPE, getIssueTypeJson(bean));
    return json;
  }

  /**
   * @param bean
   * @return
   * @throws JSONException
   */
  private JSONObject getProjectJson(Issue bean) throws JSONException {
    JSONObject json = new JSONObject();
    json.put(PROJECT_KEY, bean.getProjectName());
    return json;
  }

  /**
   * @param bean
   * @return
   * @throws JSONException
   */
  private JSONObject getIssueTypeJson(Issue bean) throws JSONException {
    JSONObject json = new JSONObject();
    json.put(ISSUETYPE_NAME, bean.getType());
    return json;
  }

}
