package iteration1;

import java.util.ArrayList;

public class LibraryController {

	public static Library lib;

	public LibraryController() {
		lib = new Library();
	}
<<<<<<< HEAD
	
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
=======

	public boolean payFines(int id, int fine) {
		if (lib.payFines(id, fine)) {
			return true;
		}
		return false;
	}

	public boolean modifyFine(String code, int fine) {
		if (lib.modifyFine(code, fine)) {
			return true;
		}
		return false;
	}

	public boolean addFines(int id, int fine) {
		if (lib.addFines(id, fine)) {
			return true;
		}
		return false;
	}

	public boolean addLibrarian(String user, String pass) {
		if (lib.addLibrarian(user, pass)) {
			return true;
		}

		return false;
	}

	public boolean deleteLibrarian(String user) {
		if (lib.deleteLibrarian(user)) {
			return true;
		}

		return false;
	}

	public boolean addCustomer(String user) {
		if (lib.addCustomer(user)) {
			return true;
		}

		return false;
	}

	public boolean deleteCustomer(int id) {
		if (lib.deleteCustomer(id)) {
			return true;
		}

		return false;
>>>>>>> dff4f274fa940f450be270bcc2f65c734851a35d
	}

	public Customer viewCustomer(int id) {
		return lib.viewCustomer(id);
	}
<<<<<<< HEAD
	
	public boolean addInventoryItem(int type, String name, String code, int quantity)
	{
		return lib.addInventoryItem(type, name, code, quantity);
	}
	
	public boolean deleteInventoryItem(String code, int quantity)
	{
		return lib.deleteInventoryItem(code, quantity);
=======

	public boolean addInventoryItem(int type, String name, String code,
			int quantity) {
		if (lib.addInventoryItem(type, name, code, quantity)) {
			return true;
		}

		return false;
	}

	public boolean deleteInventoryItem(String code, int quantity) {
		if (lib.deleteInventoryItem(code, quantity)) {
			return true;
		}

		return false;
>>>>>>> dff4f274fa940f450be270bcc2f65c734851a35d
	}

	public ArrayList<Customer> viewCustomerList() {
		return lib.viewCustomerList();
	}

	public Inventory viewInventory() {
		return lib.viewInventory();
	}

	public boolean checkOutMovie(int id, String code) {
		return lib.checkOutMovie(id, code);
	}

	public boolean checkOutBook(int id, String code) {
		return lib.checkOutBook(id, code);
	}

	public boolean checkOutMusic(int id, String code) {
		return lib.checkOutMusic(id, code);
	}
	
	public ArrayList<Item> searchByTitle(String title){
		return lib.searchByTitle(title);
	}
	public ArrayList<Item> searchByType(String type){
		return lib.searchByType(type);
	}
	public ArrayList<Item> searchByGenre(String genre){
		return lib.searchByGenre(genre);
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
<<<<<<< HEAD
	
	public boolean increaseItemQuantity(String code, int amount){
		return lib.increaseItemQuantity(code, amount);
	}
	
	public boolean modifyDueDate(int id, String newDate){
		return lib.modifyDueDate(id, newDate);
	}
	
=======

>>>>>>> dff4f274fa940f450be270bcc2f65c734851a35d
}
