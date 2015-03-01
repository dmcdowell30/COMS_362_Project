package iteration1;

import java.util.ArrayList;

public class LibraryController {

	public static Library lib;
	
	public LibraryController()
	{
		lib = new Library();
	}
	
	public boolean addLibrarian(String user, String pass)
	{
		if(lib.addLibrarian(user, pass))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean deleteLibrarian(String user)
	{
		if(lib.deleteLibrarian(user))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean addCustomer(String user)
	{
		if(lib.addCustomer(user))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean deleteCustomer(int id)
	{
		if(lib.deleteCustomer(id))
		{
			return true;
		}
		
		return false;
	}
	
	public Customer viewCustomer(int id)
	{
		Customer cust = lib.viewCustomer(id);
		
		System.out.println("Customer: " + cust);
//		System.out.println("Name: " + cust.getName());
//		System.out.println("ID: " + cust.getID);
		
		return cust;
	}
	
	public boolean addInventoryItem(String name, String type, String code, int quantity)
	{
		if(lib.addInventoryItem(name, type, code, quantity))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean deleteInventoryItem(String code, int quantity)
	{
		if(lib.deleteInventoryItem(code, quantity))
		{
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Customer> viewCustomerList()
	{
		ArrayList<Customer> custList = lib.viewCustomerList();
		
		System.out.println("Customer List: " + custList);
		
		return custList;
	}
	
	public Inventory viewInventory()
	{
		Inventory inv = lib.viewInventory();
		
		System.out.println("Inventory: " + inv);
		
		return inv;
	}
	
}
