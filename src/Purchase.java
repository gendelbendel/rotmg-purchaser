import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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
	// Used to print out errors in the request.
	// Just keep it true, as it helps the user find out if/why the program didn't work.
	static final boolean debugging = true;
	
	CountDownLatch latch = null;
	int myId;
	String email;
	String pass;
	int packageId;

    public Purchase(CountDownLatch latch, int id, String email, String pass, int packageId) {
        this.latch = latch;
        this.myId = id;
        this.email = email;
        this.pass = pass;
        this.packageId = packageId;
    }

    public void run() {

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
		
		//?guid=&newGuid=&password=&ignore=68541
		String params = String.format("guid=%s&password=%s&packageId=%s&ignore=68541", 
				theEmail, thePass, packageId);

		String request = "https://realmofthemadgod.appspot.com/account/purchasePackage";
		
		boolean result = checkIfUrlContains(request, params, "Success");
		
		return result;
    }
    
    private boolean checkIfUrlContains(String request, String urlParameters, String contents)
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

}