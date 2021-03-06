package iteration3;

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
	public boolean payFines(int id, int fine){
		Customer customer = MyDatabaseSupport.getCustomer(id);
		customer.payFine(fine);
		return MyDatabaseSupport.putCustomer(customer);
	}
	public boolean addFines(int id, int fine){
		Customer customer = MyDatabaseSupport.getCustomer(id);
		customer.addFine(fine);
		return MyDatabaseSupport.putCustomer(customer);
	}
	public boolean modifyFine(String code, int fine){
		Item item = MyDatabaseSupport.getItem(code);
		item.modifyFine(fine);
		return MyDatabaseSupport.putInventoryItem(item);
	}
	public boolean deleteCustomer(int id){
		return MyDatabaseSupport.removeCustomer(id);
	}
	public boolean deleteInventoryItem(String code, int quantity){
		return MyDatabaseSupport.removeInventoryItem(code, quantity);
	}
	public boolean addInventoryItem(int type, String name, String genre, String code, int quantity){
		Item newItem;
		if(type == Item.BOOK){
			newItem = new Book(name, genre, code, quantity, quantity);
		}
		else if(type == Item.MOVIE){
			newItem = new Movie(name, genre, code, quantity, quantity);

		}
		else if(type == Item.MUSIC){
			newItem = new Music(name, genre, code, quantity, quantity);
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
	
	public boolean checkOutMovie(int id, String code) 
	{
		Movie mov = null;
		try{mov = (Movie) MyDatabaseSupport.getItem(code);}
		catch(ClassCastException e){e.printStackTrace();
		return false;}
		
		if(mov == null)
		{
			return false;
		}
		else
		{
			Checkout checkout = new Checkout(id, mov);
			if(MyDatabaseSupport.putCheckout(checkout))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean checkOutBook(int id, String code) 
	{
		Book book = null;
		try{book = (Book) MyDatabaseSupport.getItem(code);}
		catch(ClassCastException e){e.printStackTrace();
		return false;}
		
		if(book == null)
		{
			return false;
		}
		else
		{
			Checkout checkout = new Checkout(id, book);
			if(MyDatabaseSupport.putCheckout(checkout))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean checkOutMusic(int id, String code) 
	{
		Music mus=null;
		try{mus = (Music) MyDatabaseSupport.getItem(code);}
		catch(ClassCastException e){e.printStackTrace();
		return false;}
		if(mus == null)
		{
			return false;
		}
		else
		{
			Checkout checkout = new Checkout(id, mus);
			if(MyDatabaseSupport.putCheckout(checkout))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean renewCheckout(int id){
		Checkout checkout = MyDatabaseSupport.getCheckout(id);
		System.out.println("Item: "+checkout.getItem().getName());
		System.out.println("Old Due Date: "+checkout.getDueDate());
		checkout.renew();
		
		return MyDatabaseSupport.putCheckout(checkout);
	}

	public boolean returnCheckout(int id){
		return MyDatabaseSupport.returnCheckout(id);
	}

	public ArrayList<Checkout> viewCheckOutItems(){
		return MyDatabaseSupport.getCheckOuts();
	}
	
	public boolean increaseItemQuantity(String code, int ammount) {
		Item toChange = MyDatabaseSupport.getItem(code);
		
		if(toChange == null)
			return false;
		
		toChange.increaseQuantity(ammount);
		
		return MyDatabaseSupport.putInventoryItem(toChange);
	}
	
	public boolean modifyDueDate(int id, String newDate) {
		Checkout toChange = MyDatabaseSupport.getCheckout(id);
		
		if(toChange == null)
			return false;
		
		toChange.setDueDate(newDate);
		MyDatabaseSupport.putCheckout(toChange);
		
		return true;
	}
	
	public ArrayList<Item> searchByTitle(String title){
		return MyDatabaseSupport.searchByTitle(title);
	}
	public ArrayList<Item> searchByType(int type){
		return MyDatabaseSupport.searchByType(type);
	}
	public ArrayList<Item> searchByGenre(String genre){
		return MyDatabaseSupport.searchByGenre(genre);
	}
	public ArrayList<Review> viewReviews(String itemCode) {
		return MyDatabaseSupport.viewReviews(itemCode);
	}
	public boolean addReview(String itemCode, String review) {
		return MyDatabaseSupport.addReview(itemCode, review);
	}
	public boolean deleteReview(String id) {
		return MyDatabaseSupport.deleteReview(id);
	}
}
