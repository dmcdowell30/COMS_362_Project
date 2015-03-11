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
		System.out.println("Query: "+command);
		String result = "";
		String charset = "UTF-8";
		String url = "http://databasesupport.arlenburroughs.com/db_query2.php";
		String query = "";
		
		URLConnection connection;
		String contentType = null;
		InputStream response = null;
		try {
			query = "?query="+URLEncoder.encode(command, "UTF-8");
			connection = new URL(url+query).openConnection();
			//System.out.println(url+query);
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
	////// Below are from Class Diagram //TODO
	/////////////////////////////////////
	
	boolean putLibrarian(Librarian l){
		System.out.println("Put Librarian: user:"+l.getUsername()+", pass:"+l.getPass());
		
		// determine if a current librarian exists with that username
		String query = "SELECT * FROM `librarians` WHERE username = '"+l.getUsername()+"'";
		String result = query(query);
		
		if(!result.equals(""))return false;//result wasn't empty. A librarian exists.
		
		
		query = "INSERT INTO `arlenb_coms362db`.`librarians` (`id`, `username`, `password`) "
				+ "VALUES (NULL, '"+l.getUsername()+"', '"+l.getPass()+"');";
		result = query(query);
		
		if(result.equals(""))return true;//successful insert query returns exactly nothing.
		
		return true;
	}
	
	boolean removeLibrarian(String username){
		
		// determine if the username exists.
		String query = "SELECT * FROM `librarians` WHERE username = '"+username+"'";
		String result = query(query);
				
		if(result.equals(""))return false;//result was empty. no librarian exists for username
		
		query = "DELETE FROM `arlenb_coms362db`.`librarians` WHERE `librarians`.`username` = '"+username+"'";
		result = query(query);
		
		if(result.equals(""))return true;//successful delete query returns exactly nothing.
		
		return false;
	}
	
	boolean putCustomer(Customer c){
		System.out.println("Put Customer: user:"+c.getName());
		
		String query = "INSERT INTO `customers` (`id`, `name`) "
				+ "VALUES (NULL, '"+c.getName()+"')";
		String result = query(query);
		
		if(result.equals(""))return true;//successful insert query returns exactly nothing.
		
		return false;
	}
	
	boolean removeCustomer(int id){
		// determine if the username exists.
		String query = "SELECT * FROM `customers` WHERE id = "+ id;
		String result = query(query);

		if (result.equals(""))return false;// result was empty. no librarian exists for username

		query = "DELETE FROM `customers` WHERE `id` = "+id;
		result = query(query);

		if (result.equals(""))return true;// successful delete query returns exactly nothing.

		return false;
	}
	
	Customer getCustomer(int id){
		//TODO
		Customer customer = null;
		
		String query = "SELECT * FROM `customers` WHERE id = "+ id;
		String result = query(query);
		
		if (result.equals(""))return customer;// result was empty. return null object.
		
		
		
		return null;
	}
	
	Inventory RequestInventory(){
		//TODO
		Inventory inventory = new Inventory();
		
		String query = "SELECT * FROM `items`;";
				
		String response = query(query);
		
		return inventory;
	}
	
	ArrayList<Customer> getCustomerList(){
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		String query = "SELECT * FROM 'arlenb_coms362db'.'customers';";
		String response = query(query);
		
		return customerList;
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
