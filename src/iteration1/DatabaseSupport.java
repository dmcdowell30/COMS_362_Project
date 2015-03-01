package iteration1;

import java.util.ArrayList;



public class DatabaseSupport {
	
	
	private String query(String command){
		String result = "";
		
		
		
		return result;
	}
	
	
	/////////////////////////////////////
	////// Below are from Class Diagram
	//////////////////////////
	
	
	boolean putLibrarian(Librarian l){
		boolean success = false;
		
		return success;
	}
	
	boolean removeLibrarian(String username){
		boolean success = false;
		
		return success;
	}
	
	boolean putCustomer(Customer c){
		boolean success = false;
		
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
	
	boolean putInventoryItem(Inventory i){
		boolean success = false;
		
		return success;
	}
	
	boolean removeInventoryItem(String code){
		boolean success = false;
		
		return success;
	}
}
