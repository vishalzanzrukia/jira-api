package com.vishal.jira.api.jsongen;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Vishal.Zanzrukia
 *
 * @param <T>
 */
public interface JsonGenerator<T> {
  
	/**
	 * Provides the json object which will pass through rest requesting during interaction with Jira Rest API.
	 * 
	 * @param bean
	 * @return
	 * @throws JSONException
	 */
	public JSONObject generate(T bean) throws JSONException;
}
