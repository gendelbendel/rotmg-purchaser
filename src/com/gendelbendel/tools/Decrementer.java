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
 * Decrementer.java
 * 
 * Decrementer is a simple class that uses a CountDownLatch and counts it down in one second intervals.
 * 
 * @author gendelbendel
 * See the project <a href="https://github.com/gendelbendel/rotmg-purchaser">here.</a> 
 * 
 */

public class Decrementer implements Runnable {
	
	CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
        	while(latch.getCount() != 0){
        		System.out.println("Count: " + latch.getCount());
        		Thread.sleep(1000);
                this.latch.countDown();
        	}
        	System.out.println("STARTED THREADS!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}