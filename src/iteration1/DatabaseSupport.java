package iteration1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;



public class DatabaseSupport {
	
	
	private String query(String command){
		String result = "";
		
		//TODO send query. Get Back result.
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://databasesupport.arlenburroughs.com/db_query.php");

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("query", command));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		  //convert response to string
			try{
			        BufferedReader reader = new BufferedReader(new InputStreamReader(instream,"iso-8859-1"),8);
			        StringBuilder sb = new StringBuilder();
			        String line = null;
			        while ((line = reader.readLine()) != null) {
			                sb.append(line + "\n");
			        }
			        instream.close();
			        result=sb.toString();
			        
			}catch(Exception e){
			        return "CF";
			}
			
			return result;
		}
		
		System.out.println(result);
		return result;
	}
	
	
	/////////////////////////////////////
	////// Below are from Class Diagram
	//////////////////////////
	
	
	boolean putLibrarian(Librarian l){
		boolean success = false;
		
		String query = "INSERT INTO 'arlenb_coms362db'.'librarians' ('id', 'username', 'password') "+
		"VALUES (NULL, '"+l.getUsername()+"', '"+"librarian password"+"');";
		
		query(query);
		
		return success;
	}
	
	boolean removeLibrarian(String username){
		boolean success = false;
		
		String query = "DELETE FROM 'arlenb_coms362db'.'librarians' WHERE 'username' = '"+username+"';";
		String result = query(query);
		
		if(result.length()>0)success = true;
		
		return success;
	}
	
	boolean putCustomer(Customer c){
		boolean success = false;
		
		String query = "INSERT INTO 'arlenb_coms362db'.'customers' ('id', 'username', 'password') "+
				"VALUES (NULL, '"+c.getName()+"', '"+"customer password"+"');";
				
		String response = query(query);
		
		return success;
	}
	
	Inventory RequestInventory(){
		Inventory inventory = new Inventory();
		
		String query = "SELECT * FROM 'arlenb_coms362db'.'inventory';";
				
		String response = query(query);
		
		return inventory;
	}
	
	Customer getCustomer(int id){
		Customer customer;
		
		String query = "SELECT * FROM 'arlenb_coms362db'.'customers'"+
		"WHERE id = '"+id+"'";
		String response = query(query);
		
		return null;
	}
	
	ArrayList<Customer> getCustomerList(){
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		String query = "SELECT * FROM 'arlenb_coms362db'.'customers';";
		String response = query(query);
		
		return customerList;
	}
	
	boolean removeCustomer(int id){
		boolean success = false;
		
		return success;
	}
	
	boolean putInventoryItem(Item i){
		boolean success = false;
		
		return success;
	}
	
	boolean removeInventoryItem(String code, int quantity){
		boolean success = false;
		
		return success;
	}
}
