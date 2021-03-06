package cl.ciochile.victoraravena;


import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 
 * @author Victor Aravena victor.aravena@ciochile.cl
 *
 */

public class ApiAuthRest {
	
	static String username = null;
	static String password = null;
	static String URLBase = null;
	
	/**
	 * HTTP POST
	 * @param URLPath
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Boolean getRequestPost(String URLPath, StringEntity input) throws Exception {
        String URL = URLBase;
        Boolean response =  false;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
        	HttpPost httpPost = new HttpPost(URL);
        	System.out.println(URL);
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpPost);
            httpPost.setHeader(authorizationHeader);
            httpPost.setEntity(input);
            //System.out.println("Executing request: " + httpGet.getRequestLine());
            //System.out.println(response);
//            response = httpclient.execute(httpGet,responseHandler);
            HttpResponse responseRequest = httpclient.execute(httpPost);
            
    		if (responseRequest.getStatusLine().getStatusCode() != 204 && responseRequest.getStatusLine().getStatusCode() != 201) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ responseRequest.getStatusLine().getStatusCode());
    		}
     
    		
    		httpclient.getConnectionManager().shutdown();
    		response = true;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }
	
	/**
	 * HTTP PUT
	 * @param string 
	 * @param sismaCodes
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public static Boolean getRequestPutProcessed(String urlPathProcessed, List<String> sismaCodes) throws Exception {
        String URL = URLBase + urlPathProcessed;
        Boolean response =  false;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
        	
        	URIBuilder uriBuilder = new URIBuilder(URL);
			for (String sismaCode : sismaCodes) {
				uriBuilder.addParameter("processedNids",sismaCode);
			}
        	
        	HttpPut httpPut = new HttpPut(uriBuilder.build()); 
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpPut);
            httpPut.setHeader(authorizationHeader);

            HttpResponse responseRequest = httpclient.execute(httpPut);
            
    		/*if (responseRequest.getStatusLine().getStatusCode() != 204 && responseRequest.getStatusLine().getStatusCode() != 201) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ responseRequest.getStatusLine().getStatusCode());
    		}*/
    		
    		httpclient.getConnectionManager().shutdown();
    		response = true;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }
	
	/**
	 * HTTP PUT
	 * @param string 
	 * @param sismaCodes
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unused" })
	public static Boolean getRequestPutNotProcessed(String urlPathNotProcessed, List<String> sismaCodes) throws Exception {
        String URL = URLBase + urlPathNotProcessed;
        Boolean response =  false;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
        	
        	URIBuilder uriBuilder = new URIBuilder(URL);
			for (String sismaCode : sismaCodes) {
				uriBuilder.addParameter("notProcessedNids",sismaCode);
			}
        	
        	HttpPut httpPut = new HttpPut(uriBuilder.build()); 
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpPut);
            httpPut.setHeader(authorizationHeader);

            HttpResponse responseRequest = httpclient.execute(httpPut);
            
    		/*if (responseRequest.getStatusLine().getStatusCode() != 204 && responseRequest.getStatusLine().getStatusCode() != 201) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ responseRequest.getStatusLine().getStatusCode());
    		}*/
    		
    		httpclient.getConnectionManager().shutdown();
    		response = true;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }
	
	/**
	 * HTTP GET
	 * @param sismaCodes 
	 * @param sismaCodes 
	 * @param URLPath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String getRequestGet(List<String> sismaCodes) throws Exception {
        String URL = URLBase;
        String response =  "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        
        try {
        	
        	URIBuilder uriBuilder = new URIBuilder(URL);
        			for (String sismaCode : sismaCodes) {
        				uriBuilder.addParameter("locationCodes",sismaCode);
			}
        		            	
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpGet);
            httpGet.setHeader(authorizationHeader);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            
            response = httpclient.execute(httpGet,responseHandler);
           
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }
	
	
	/**
	 * HTTP GET
	 * @param URLPath
	 * @return
	 * @throws Exception
	 */
	public static String getRequestGet(String URLPath) throws Exception {
        String URL = URLBase + URLPath;
        String response =  "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet(URL);

            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpGet);
            httpGet.setHeader(authorizationHeader);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            
            System.out.println("Executing request: " + httpGet.getRequestLine());
            System.out.println(response);
            response = httpclient.execute(httpGet,responseHandler);
            
           
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }
	
	
	/**
	 * HTTP GET
	 * @param sismaCodes 
	 * @param sismaCodes 
	 * @param URLPath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String getRequestGetFsrByStatus(String urlPathProcessed, List<String> sismaCodes, String viralLoadStatus) throws Exception {
        String URL = URLBase + urlPathProcessed;
        String response =  "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        
        try {
        	
        	URIBuilder uriBuilder = new URIBuilder(URL);
        	uriBuilder.addParameter("locationCodes",sismaCodes.get(0));
        	uriBuilder.addParameter("viralLoadStatus",viralLoadStatus);
        	
        		            	
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            BasicScheme scheme = new BasicScheme();
            Header authorizationHeader = scheme.authenticate(credentials, httpGet);
            httpGet.setHeader(authorizationHeader);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            
            response = httpclient.execute(httpGet,responseHandler);
           
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return response;
    }


	public static void setUsername(String username) {
		ApiAuthRest.username = username;
	}


	public static void setPassword(String password) {
		ApiAuthRest.password = password;
	}


	public static void setURLBase(String uRLBase) {
		URLBase = uRLBase;
	}
  
}