package com.gendelbendel.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/* URLContent.java
 * 
 * URLContent is used for sending a request to obtain data from RotMG's data servers.
 * 
 * @author gendelbendel
 * 
 * 
 */

public class URLContent 
{

	// Used to print out errors in the request.
	// Just keep it true, as it helps the user find out if/why the program didn't work.
	static final boolean debugging = true;
	
	private String m_request;
	private String m_urlParameters;
	private String m_contents;
	
	public URLContent(String m_request, String m_urlParameters, String m_contents) 
	{
		this.m_request = m_request;
		this.m_urlParameters = m_urlParameters;
		this.m_contents = m_contents;
	}
	
	public static boolean check(String request, String urlParameters, String contents)
	{
		boolean result = false;
		URL url;
		HttpURLConnection connection;
		try {
			url = new URL(request);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("x-flash-version", "11,2,202,235");
			connection.setRequestProperty("User-Agent", "Shockwave Flash");
			connection.setRequestProperty("Host", "realmofthemadgod.appspot.com");
			connection.setRequestProperty("Referer", 
										"http://www.realmofthemadgod.com/AssembleeGameClient1350071502.swf");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + 
										Integer.toString(urlParameters.getBytes().length));
			connection.setUseCaches (false);
			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(urlParameters);
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String line;
		    
		    while ((line = rd.readLine()) != null) {
		    	
		    	if(line.contains(contents))
		    	{
		    		result = true;
		    	}
		    	else
		    	{
		    		if(debugging)
		    			System.out.println("	" + line);
		    	}
		    }
		    wr.close();
		    rd.close();
			connection.disconnect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean check()
	{
		boolean result = false;
		URL url;
		HttpURLConnection connection;
		try {
			url = new URL(m_request);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("x-flash-version", "11,2,202,235");
			connection.setRequestProperty("User-Agent", "Shockwave Flash");
			connection.setRequestProperty("Host", "realmofthemadgod.appspot.com");
			connection.setRequestProperty("Referer", 
										"http://www.realmofthemadgod.com/AssembleeGameClient1350071502.swf");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + 
										Integer.toString(m_urlParameters.getBytes().length));
			connection.setUseCaches (false);
			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(m_urlParameters);
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    String line;
		    
		    while ((line = rd.readLine()) != null) {
		    	
		    	if(line.contains(m_contents))
		    	{
		    		result = true;
		    	}
		    	else
		    	{
		    		if(debugging)
		    			System.out.println("	" + line);
		    	}
		    }
		    wr.close();
		    rd.close();
			connection.disconnect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
