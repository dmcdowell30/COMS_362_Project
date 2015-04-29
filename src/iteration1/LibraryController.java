package iteration1;

import java.util.ArrayList;

public class LibraryController {

	public static Library lib;
	
	public LibraryController()
	{
		lib = new Library();
	}
	
	public boolean payFines(int id, int fine)
	{
		return lib.payFines(id, fine);
	}
	
	public boolean modifyFine(String code, int fine)
	{
		return lib.modifyFine(code, fine);
	}
	
	public boolean addFines(int id, int fine)
	{
		return lib.addFines(id, fine);
	}
	
	public boolean addLibrarian(String user, String pass)
	{
		return lib.addLibrarian(user, pass);
	}
	
	public boolean deleteLibrarian(String user)
	{
		return lib.deleteLibrarian(user);
	}
	
	public boolean addCustomer(String user)
	{
		return lib.addCustomer(user);
	}
	
	public boolean deleteCustomer(int id)
	{
		return lib.deleteCustomer(id);
	}
	
	public Customer viewCustomer(int id)
	{
		return lib.viewCustomer(id);
	}
	
	public boolean addInventoryItem(int type, String name, String genre, String code, int quantity)
	{
		return lib.addInventoryItem(type, name, genre, code, quantity);
	}
	
	public boolean deleteInventoryItem(String code, int quantity)
	{
		return lib.deleteInventoryItem(code, quantity);
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
	
	public boolean renewCheckout(int id){
		return lib.renewCheckout(id);
	}

	public boolean returnCheckout(int id){
		return lib.returnCheckout(id);
	}

	public ArrayList<Checkout> viewCheckOutItems(){
		return lib.viewCheckOutItems();
	}
	
	public boolean increaseItemQuantity(String code, int amount){
		return lib.increaseItemQuantity(code, amount);
	}
	
	public boolean modifyDueDate(int id, String newDate){
		return lib.modifyDueDate(id, newDate);
	}
	
	public ArrayList<Item> searchByTitle(String title){
		return lib.searchByTitle(title);
	}
	public ArrayList<Item> searchByType(int type){
		return lib.searchByType(type);
	}
	public ArrayList<Item> searchByGenre(String genre){
		return lib.searchByGenre(genre);
	}

	public ArrayList<Review> viewReviews(String itemCode) {
		return lib.viewReviews(itemCode);
	}

	public boolean addReview(String itemCode, String review) {
		return lib.addReview(itemCode, review);
	}

	public boolean deleteReview(String id) {
		return lib.deleteReview(id);
	}
	
}
