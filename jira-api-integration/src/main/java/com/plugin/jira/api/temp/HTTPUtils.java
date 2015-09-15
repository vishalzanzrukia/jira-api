package com.plugin.jira.api.temp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * It will contain all the methods related to HTTP connection
 * 
 * @author vishal.zanzrukia
 * @version 1.0
 * @since 
 */
public class HTTPUtils {
  
  
  private static final String SHOPRUNNER_API_PASSWORD = "KEuJg1a8j2k"; 
  private static final String SHOPRUNNER_API_USERNAME = "claire@shoprunner.com.shoprunner";
  private static final String SHOPRUNNER_API_URL = "https://api.shoprunner.com/validateToken";
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
    URL url = new URL(SHOPRUNNER_API_URL);
    connection = (HttpURLConnection)url.openConnection();
    connection.setRequestMethod(POST);
   
    //set max age similar to token expire time so that cookies shall remove automatically
   /* if(connectionDetails.get(Constants.SHOPRUNNER_DETAIL.TOKEN_EXPIRY_TIME.toString()) != null){
        SHOPRUNNER_COOKIE_MAX_AGE = Integer.valueOf((String)connectionDetails.get(Constants.SHOPRUNNER_DETAIL.TOKEN_EXPIRY_TIME.toString()));
    }*/
    
    // Create the Authorization Header
    byte[] bytes =(SHOPRUNNER_API_USERNAME+ ":" + SHOPRUNNER_API_PASSWORD).getBytes();
    String encodedCredential = new String(Base64.encodeBase64(bytes)); 
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
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      
     /* String line = null;
      StringBuffer sbf = new StringBuffer();
      while ((line = br.readLine()) != null) {
//        System.out.println(line);
        sbf.append(line);
//        result+= line;
       }*/
      
//      System.out.println("response : "+sbf.toString());
      
      StringBuilder response = new StringBuilder();
      String strBuffer = "";
      try {
          while (strBuffer != null) {
              response.append(strBuffer);
              response.append("\n");
              strBuffer = br.readLine();
          }
          
          
         /* InputStream responseBodyStream = connection.getInputStream();
          while ((read = responseBodyStream.read(buffer)) != -1) {
            response.append(new String(buffer, 0, read));
          }*/
          
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
  
  public static Document getXmlStringAsDocument(String xml, boolean validate) throws ParserConfigurationException, IOException, SAXException {

		InputSource is = new InputSource(new ByteArrayInputStream(xml.getBytes()));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


		dbf.setNamespaceAware(true);
		dbf.setValidating(validate);

		DocumentBuilder db = dbf.newDocumentBuilder();
//		db.setErrorHandler(new DocBuilderLog4jErrorHandler(log));

		Document doc = db.parse(is);

		return doc;
	}

  
  public static void main(String[] args) throws MalformedURLException, FileNotFoundException, IOException, ParserConfigurationException, SAXException {
    HTTPUtils util =  new HTTPUtils();
    util.initialiseServer();
    
    
    Document xmlDocument = getXmlStringAsDocument(util.validateToken("cc2f8a5c90142f209db66de5391f6f22"), false);
	System.out.println("done parsing");
  }
}
