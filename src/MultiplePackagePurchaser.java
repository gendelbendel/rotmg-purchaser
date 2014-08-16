import java.util.concurrent.CountDownLatch;


/* MultiplePackagePurchaser.java
 * 
 * MultiplePackagePurchaser is used for sending multiple requests at the same time
 * to purchase a package multiple times.
 * 
 * This was written to attempt to receieve multiple free backpacks during the promo, where
 * it was successful in doing so.
 * 
 * @author gendelbendel
 * 
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
