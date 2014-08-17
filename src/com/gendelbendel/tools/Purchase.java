package com.gendelbendel.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;


/* Purchase.java
 * 
 * Purchase is used for sending a request to purchase a package specified in the constructor.
 * 
 * @author gendelbendel
 * 
 * 
 */

public class Purchase implements Runnable 
{
	
	
	CountDownLatch latch = null;
	int myId;
	String email;
	String pass;
	int packageId;

    public Purchase(CountDownLatch latch, int id, String email, String pass, int packageId) 
    {
        this.latch = latch;
        this.myId = id;
        this.email = email;
        this.pass = pass;
        this.packageId = packageId;
    }

    public void run() 
    {

        try {
        	latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Starting: " + this.myId);
        boolean success = purchasePackage();
        if(success)
        	System.out.println("Success Thread:  " + this.myId);
        
    }
    
    private boolean purchasePackage()
    {
    	
    	String theEmail = email;
		String thePass = pass;
		
		try {
			theEmail = URLEncoder.encode(theEmail, "UTF-8");
			thePass = URLEncoder.encode(thePass, "UTF-8");
		}
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String params = String.format("guid=%s&password=%s&packageId=%s&ignore=68541", 
				theEmail, thePass, packageId);

		String request = "https://realmofthemadgod.appspot.com/account/purchasePackage";
		
		boolean result = new URLContent(request, params, "Success").check();
		
		return result;
    }


}