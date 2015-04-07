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
		return lib.viewCustomer(id);
	}
	
	public boolean addInventoryItem(int type, String name, String code, int quantity)
	{
		if(lib.addInventoryItem(type, name, code, quantity))
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
		return lib.viewCustomerList();
	}
	
	public Inventory viewInventory()
	{
		return lib.viewInventory();
	}
	
	public boolean checkOutMovie(int id, String code)
	{
		return lib.checkOutMovie(id, code);
	}
	
	public boolean checkOutBook(int id, String code)
	{
		return lib.checkOutBook(id, code);
	}
	
	public boolean checkOutMusic(int id, String code)
	{
		return lib.checkOutMusic(id, code);
	}
	
}
