package com.gendelbendel.tools;

import java.util.concurrent.CountDownLatch;

/* Decrementer.java
 * 
 * Decrementer is a simple class that uses a CountDownLatch and counts it down in one second intervals.
 * 
 * @author gendelbendel
 * 
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