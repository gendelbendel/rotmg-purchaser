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

import java.util.concurrent.CountDownLatch;


/* 
 * MultiplePackagePurchaser.java
 * 
 * MultiplePackagePurchaser is used for sending multiple requests at the same time
 * to purchase a package multiple times.
 * 
 * This was written to attempt to receieve multiple free backpacks during the promo, where
 * it was successful in doing so.
 * 
 * @author gendelbendel
 * See the project <a href="https://github.com/gendelbendel/rotmg-purchaser">here.</a> 
 * 
 */

public class MultiplePackagePurchaser 
{

	/*
	 * ONLY CHANGE INFORMATION BELOW THIS COMMENT BLOCK
	 * 
	 */
	
	// The email for your RotMG account
	public static String yourEmail = "test@gmail.com";
	
	// The password for your RotMG account
	public static String yourPassword = "testpassword";
	
	// The package id of the package you'd like to purchase
	public static int packageId = 377;
	
	// The amount of times you'd like to attempt to purchase the package.
	// Anything above 100 doesn't typically work well.
	public static int timesToRun = 100; 
	
	/*
	 * ONLY CHANGE INFORMATION ABOVE THIS COMMENT BLOCK
	 * 
	 * 
	 */
	
	public static void main(String[] args)
	{
		try {
			manyPurchases(timesToRun);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void manyPurchases(int amt) throws InterruptedException
	{
		CountDownLatch myLatch = new CountDownLatch(3);
		
		Decrementer dec = new Decrementer(myLatch);
		
		for(int i = 0; i < amt; i++)
		{
			new Thread(new Purchase(myLatch, i, yourEmail, yourPassword, packageId)).start();
		}
		System.out.println("Starting countdown.");
		new Thread(dec).start();
				
	}
}
