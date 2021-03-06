package com.plugin.jira.api.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.codec.binary.Base64;

/**
 * It will contain all the methods related to HTTP connection
 * 
 * @author vishal.zanzrukia
 * @version 1.0
 * @since 
 */
public class HTTPUtils {
  
  
  private static final String API_PASSWORD = "TopSecret!"; 
  private static final String API_USERNAME = "something@something";
  private static final String API_URL = "https://something.com/helloword";
  private static final String POST = "POST";
  private static HttpURLConnection connection = null;
  
  private static HostnameVerifier hv = new HostnameVerifier()
  {
      public boolean verify(String urlHostName, SSLSession session)
      {
          String peerHost = session.getPeerHost();
          if(!urlHostName.equals(peerHost)) {
            System.err.println("Warning: URL Host: " + urlHostName + " vs. " + peerHost);
            
          }
          return true;
      }
  };
  
  public void initialiseServer() throws ProtocolException, MalformedURLException, IOException{
    HttpsURLConnection.setDefaultHostnameVerifier(hv);  
    URL url = new URL(API_URL);
    connection = (HttpURLConnection)url.openConnection();
    connection.setRequestMethod(POST);
   
    //set max age similar to token expire time so that cookies shall remove automatically
   /* if(connectionDetails.get(Constants.SHOPRUNNER_DETAIL.TOKEN_EXPIRY_TIME.toString()) != null){
        SHOPRUNNER_COOKIE_MAX_AGE = Integer.valueOf((String)connectionDetails.get(Constants.SHOPRUNNER_DETAIL.TOKEN_EXPIRY_TIME.toString()));
    }*/
    
    // Create the Authorization Header
    String encodedCredential = Base64.encodeBase64String((API_USERNAME+ ":" + API_PASSWORD).getBytes()); 
    connection.setRequestProperty("Authorization", "BASIC " + encodedCredential);
    connection.setDoOutput(true);
    
}
  
  public String validateToken(String token) throws MalformedURLException, FileNotFoundException, IOException
  {
      initialiseServer();
      StringBuffer body = new StringBuffer();
      body.append("srtoken=").append(URLEncoder.encode(token, "UTF-8"));
      byte buffer[] = body.toString().getBytes(); 
      int read = 0;
      OutputStream output = connection.getOutputStream();
      output.write(buffer, 0, body.length());
      
      connection.connect();
   // Read it all and print it out
//      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      
     /* String line = null;
      StringBuffer sbf = new StringBuffer();
      while ((line = br.readLine()) != null) {
//        System.out.println(line);
        sbf.append(line);
//        result+= line;
       }*/
      
//      System.out.println("response : "+sbf.toString());
      
      StringBuilder response = new StringBuilder();
//      String strBuffer = "";
      try {
         /* while (strBuffer != null) {
              response.append(strBuffer);
              response.append("\n");
              strBuffer = br.readLine();
          }*/
          
          
          InputStream responseBodyStream = connection.getInputStream();
          while ((read = responseBodyStream.read(buffer)) != -1) {
            response.append(new String(buffer, 0, read));
          }
          
      }
      catch (IOException ioe) {
          ioe.printStackTrace();
      }finally{
          /*if(br != null){
             br.close();
          }*/
          connection.disconnect();
      }
      
//      System.out.println(response);
      return response.toString(); 
  }

  
  public static void main(String[] args) throws MalformedURLException, FileNotFoundException, IOException {
    HTTPUtils util =  new HTTPUtils();
    util.initialiseServer();
    
    System.out.println(util.validateToken("cc2f8a5c90142f209db66de5391f6f22"));
  }
}
