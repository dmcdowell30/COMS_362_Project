package iteration1;

import java.io.DataOutputStream;
import java.io.IOException;
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
		
		System.out.println(result);
		return result;
	}
	
	
	/////////////////////////////////////
	////// Below are from Class Diagram
	//////////////////////////
	
	
	boolean putLibrarian(Librarian l){
		boolean success = false;
		//TODO
		String query = "INSERT INTO 'arlenb_coms362db'.'librarians' ('id', 'username', 'password') "+
		"VALUES (NULL, '"+l.getUsername()+"', '"+l.getPassword()+"');";
		
		query(query);
		
		return success;
	}
	
	boolean removeLibrarian(String username){
		boolean success = false;
		
		String query = "INSERT INTO 'arlenb_coms362db'.'librarians' ('id', 'username', 'password') "+
				"VALUES (NULL, '"+l.getUsername()+"', '"+l.getPassword()+"');";
				
		query(query);
		
		return success;
	}
	
	boolean putCustomer(Customer c){
		boolean success = false;
		//TODO
		return success;
	}
	
	Inventory RequestInventory(){
		Inventory inventory = new Inventory();
		
		return inventory;
	}
	
	Customer getCustomer(int id){
		Customer customer;
		
		return null;
	}
	
	ArrayList<Customer> getCustomerList(){
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
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
