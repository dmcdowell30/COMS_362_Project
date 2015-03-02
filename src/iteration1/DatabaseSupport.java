package iteration1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;



public class DatabaseSupport {
	
	
	public String query(String command){
		String result = "";
		String charset = "UTF-8";
		String url = "http://databasesupport.arlenburroughs.com/db_query.php";
		String query = "";
		
		URLConnection connection;
		String contentType = null;
		InputStream response = null;
		try {
			query = "?query="+URLEncoder.encode(command, "UTF-8");
			connection = new URL(url+query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			response = connection.getInputStream();
			
			contentType = connection.getHeaderField("Content-Type");
		} catch (IOException e1) {
			System.out.println("Error1");
			e1.printStackTrace();
		}

		if (charset != null) {
		    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
		        for (String line; (line = reader.readLine()) != null;) {
		            result = result+line;
		        }
		    } catch (IOException e) {
		    	System.out.println("Error2");
				e.printStackTrace();
			}
		}

		System.out.println("From DB: "+result);
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
