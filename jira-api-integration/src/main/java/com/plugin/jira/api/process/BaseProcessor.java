package com.plugin.jira.api.process;

import com.plugin.jira.api.excel.ExcelConfig;
import com.plugin.jira.api.jsongen.JsonGenerator;



/**
 * @author Vishal.Zanzrukia
 * 
 */
public abstract class BaseProcessor<T> implements Processor<T> {

  private JsonGenerator<T> jsonGenerator;

  public BaseProcessor(T object, JsonGenerator<T> jsonGenerator, ExcelConfig config) {
    jsonGenerator = this.jsonGenerator;
  }

  public void sendRequest() {
    String jsonString = this.jsonGenerator.toString();
    // HttpPost post = new HttpPost(url);

    /*
     * 
     * HttpPost post = new HttpPost(url);
     * 
     * // add header post.setHeader("Host", "accounts.google.com");
     * post.setHeader("Accept-Language", "en-US,en;q=0.5"); post.setHeader("Connection",
     * "keep-alive"); post.setHeader("Referer", "https://accounts.google.com/ServiceLoginAuth");
     * post.setHeader("Content-Type", "application/x-www-form-urlencoded");
     * 
     * post.setEntity(new UrlEncodedFormEntity(postParams));
     * 
     * HttpResponse response = client.execute(post);
     * 
     * int responseCode = response.getStatusLine().getStatusCode();
     * 
     * System.out.println("\nSending 'POST' request to URL : " + url);
     * System.out.println("Post parameters : " + postParams); System.out.println("Response Code : "
     * + responseCode);
     * 
     * BufferedReader rd = new BufferedReader(new
     * InputStreamReader(response.getEntity().getContent()));
     * 
     * StringBuffer result = new StringBuffer(); String line = ""; while ((line = rd.readLine()) !=
     * null) { result.append(line); }
     * 
     * // System.out.println(result.toString());
     */
  }

}
