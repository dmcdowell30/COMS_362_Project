package iteration1;

import java.util.ArrayList;

public class Library {
	
	public Library(){
		
	}
	private DatabaseSupport MyDatabaseSupport = new DatabaseSupport();

	public boolean deleteLibrarian(String username){
		return MyDatabaseSupport.removeLibrarian(username);
	}
	public boolean addLibrarian(String name, String pass){
		Librarian newLibrarian = new Librarian(name, pass); 
		return MyDatabaseSupport.putLibrarian(newLibrarian);
	}
	public boolean addCustomer(String name){
		Customer newCustomer = new Customer(name);
		return MyDatabaseSupport.putCustomer(newCustomer);
	}
	public boolean deleteCustomer(int id){
		return MyDatabaseSupport.removeCustomer(id);
	}
	public boolean deleteInventoryItem(String code, int quantity){
		return MyDatabaseSupport.removeInventoryItem(code, quantity);
	}
	public boolean addInventoryItem(int type, String name, String code, int quantity){
		Item newItem;
		if(type == Item.BOOK){
			newItem = new Book(name, code, quantity);
		}
		else if(type == Item.MOVIE){
			newItem = new Movie(name, code, quantity);

		}
		else if(type == Item.MUSIC){
			newItem = new Music(name, code, quantity);
		}
		else{
			return false;
		}
		return MyDatabaseSupport.putInventoryItem(newItem);
	}
	public Inventory viewInventory(){
		return MyDatabaseSupport.RequestInventory();
	}
	public Customer viewCustomer(int id){
		return MyDatabaseSupport.getCustomer(id);
	}
	public ArrayList<Customer> viewCustomerList(){
		return MyDatabaseSupport.getCustomerList();
	}
}
