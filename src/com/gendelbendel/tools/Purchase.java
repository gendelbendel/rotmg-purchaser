/* 
 * Copyright 2014 gendelbendel
 * 
 * This file is part of rotmg-purchaser.
 *
 * rotmg-purchaser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * rotmg-purchaser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.gendelbendel.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;


/* 
 * Purchase.java
 * 
 * Purchase is used for sending a request to purchase a package specified in the constructor.
 * 
 * @author gendelbendel
 * See the project <a href="https://github.com/gendelbendel/rotmg-purchaser">here.</a> 
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