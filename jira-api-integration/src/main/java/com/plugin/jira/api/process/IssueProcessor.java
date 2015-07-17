package com.plugin.jira.api.process;

import com.plugin.jira.api.domain.Issue;
import com.plugin.jira.api.excel.ExcelConfig;
import com.plugin.jira.api.jsongen.JsonGenerator;

/**
 * It will process the issue and send the final request to server.
 * 
 * @author Vishal.Zanzrukia
 * 
 */
public class IssueProcessor extends BaseProcessor<Issue> {


  public IssueProcessor(Issue object, JsonGenerator<Issue> jsonGenerator, ExcelConfig config) {
    super(object, jsonGenerator, config);
  }

  @Override
  public void process(Issue object, ExcelConfig config) {

  }
}
