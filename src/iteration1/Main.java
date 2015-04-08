package iteration1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		LibraryController libctrl = new LibraryController();
		
		while(true)
		{
			System.out.println("\nWhat would you like to do?");
			System.out.println("1) Add something");
			System.out.println("2) Delete something");
			System.out.println("3) View a Customer");
			System.out.println("4) View Customer List");
			System.out.println("5) View Inventory");
			System.out.println("6) Pay Fines");
			System.out.println("7) Modify Fines");
			System.out.println("8) Add Fines");
			System.out.println("9) Check out Movie");
			System.out.println("10) Check out Music");
			System.out.println("11) Check out Book");
			System.out.println("12) Return Checkout");
			System.out.println("13) Renew Checkout");
			System.out.println("14) View all Checkouts");
			System.out.println("15) Exit");
			
			System.out.print("Choice: ");
			String in = input.nextLine();
			System.out.println();
			
			boolean success;
			if(in.equals("1"))// add
			{
				System.out.println("What would you like to add?");
				System.out.println("1) Librarian");
				System.out.println("2) Customer");
				System.out.println("3) New Inventory Item");
				System.out.println("Choice: ");
				
				in = input.nextLine();
				
				if(in.equals("1"))
				{
					System.out.print("Enter username first: ");
					String user = input.nextLine();
					
					System.out.print("Enter password first: ");
					String pass = input.nextLine();
					
					success = libctrl.addLibrarian(user, pass);
				}
				else if(in.equals("2"))
				{
					System.out.print("Enter username: ");
					String user = input.nextLine();
					
					success = libctrl.addCustomer(user);
				}
				else if(in.equals("3"))
				{
					System.out.print("Enter item type (0:Book, 1:Movie, 2:Music): ");
					String itemType = input.nextLine();
					int type = Integer.parseInt(itemType);
					
					System.out.print("Enter item name: ");
					String name = input.nextLine();
					
					System.out.print("Enter item code: ");
					String code = input.nextLine();

					System.out.print("Enter item quantity: ");
					String quan = input.nextLine();
					int quantity = Integer.parseInt(quan);
					
					success = libctrl.addInventoryItem(type, name, code, quantity);
				}
				else
				{
					System.out.println("Command not recognized. Exiting program...");
					break;
				}
				
				if(success)System.out.println("\nAdd Complete!");
				else System.out.println("\nAdd Failed.");
				
			}
			else if(in.equals("2"))// delete
			{
				System.out.println("What would you like to delete?");
				System.out.println("1) Librarian");
				System.out.println("2) Customer");
				System.out.println("3) Inventory Item");
				System.out.println("Choice: ");
				
	
				in = input.nextLine();
				
				if(in.equals("1"))
				{
					System.out.print("Enter librarian username to remove: ");
					String user = input.nextLine();
					
					success = libctrl.deleteLibrarian(user);
				}
				else if(in.equals("2"))
				{
					System.out.print("Enter customer id to remove: ");
					String id = input.nextLine();
					int ID = Integer.parseInt(id);
					
					success = libctrl.deleteCustomer(ID);
				}
				else if(in.equals("3"))
				{
					System.out.print("Enter item code to remove: ");
					String code = input.nextLine();

					System.out.print("Enter item quantity: ");
					String quan = input.nextLine();
					int quantity = Integer.parseInt(quan);
					
					
					success = libctrl.deleteInventoryItem(code, quantity);
				}
				else
				{
					System.out.println("Command not recognized. Exiting program...");
					break;
				}
				if(success)System.out.println("\nRemove Operation Complete!");
				else System.out.println("\nRemove Operation Failed.");
				
			}
			else if(in.equals("3"))// view a customer
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				
				Customer cust = libctrl.viewCustomer(id);
				if(cust == null)System.out.println("Lookup Failed - Null Customer Received");
				else{
					System.out.println("Customer id: "+cust.getId()+"\t name: "+cust.getName()+"\t Fines: $"+cust.getFines());
				}
			}
			else if(in.equals("4")) // view customer list
			{
				ArrayList<Customer> custList = libctrl.viewCustomerList();
				for(Customer customer: custList){
					System.out.println("Customer id: "+customer.getId()+"\t name: "+customer.getName()+"\t Fines: $"+customer.getFines());
				}
			}
			else if(in.equals("5")) // view inventory
			{
				Inventory inventory = libctrl.viewInventory();
				
				for(Item item:inventory.getInventory()){
					String title = "\""+item.getName()+"\"";
					while(title.length()<25)title = title+" ";
					String code = ""+item.getCode();
					while(code.length()<15)code = code+" ";
					System.out.println(item.getTypeString()+":\t"+title+"\t code:"+code+
							"\t stock/avail: "+item.getQuantity()+"/"+item.getAvail());
				}
			}
			//pay fines
			else if(in.equals("6"))
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				
				System.out.print("Enter amount to pay: ");
				String line = input.nextLine();
				int fine = Integer.parseInt(line);
				
				if(libctrl.payFines(id, fine) == false){
					System.out.println("Could not find customer");
				}
				else{
					System.out.println("Successfully paid fines");
				}
			}
			//modify fines
			else if(in.equals("7"))
			{
				System.out.print("Enter item id: ");
				String id = input.nextLine();
				
				System.out.print("Enter new fine amount: ");
				String line = input.nextLine();
				int fine = Integer.parseInt(line);
				
				if(libctrl.modifyFine(id, fine) == false){
					System.out.println("Could not find item");
				}
				else{
					System.out.println("Successfully changed items fine");
				}
			}
			//add fines
			else if(in.equals("8"))
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				
				System.out.print("Enter amount to fine customer: ");
				String line = input.nextLine();
				int fine = Integer.parseInt(line);
				
				if(libctrl.addFines(id, fine) == false){
					System.out.println("Could not find customer");
				}
				else{
					System.out.println("Successfully added fines");
				}
			}
			//checkout movie
			else if(in.equals("9"))
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				System.out.print("Enter Movie code: ");
				String code = input.nextLine();
				
				if(libctrl.checkOutMovie(id, code))
				{
					System.out.println("Movie checkout success");
				}
				else
				{
					System.out.println("Movie checkout failed");
				}
			}
			//checkout music
			else if(in.equals("10"))
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				System.out.print("Enter Music code: ");
				String code = input.nextLine();
				
				if(libctrl.checkOutMusic(id, code))
				{
					System.out.println("Music checkout success");
				}
				else
				{
					System.out.println("Music checkout failed");
				}
			}
			//checkout book
			else if(in.equals("11"))
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				System.out.print("Enter Book code: ");
				String code = input.nextLine();
				
				if(libctrl.checkOutBook(id, code))
				{
					System.out.println("Book checkout success");
				}
				else
				{
					System.out.println("Book checkout failed");
				}
			}
			//Return Checkout
			else if(in.equals("12"))
			{
				System.out.print("Enter checkout id: ");
				int id = Integer.parseInt(input.nextLine());
				
				if(libctrl.returnCheckout(id))
				{
					System.out.println("Checkout Return succeeded");
				}
				else
				{
					System.out.println("Checkout Return failed");
				}
			}
			//Renew Checkout
			else if(in.equals("13"))
			{
				System.out.print("Enter checkout id to renew: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);

				if(libctrl.renewCheckout(id))
				{
					System.out.println("Renew success");
				}
				else
				{
					System.out.println("Renew failed");
				}
			}
			//View Checkouts
			else if(in.equals("14"))
			{
				ArrayList<Checkout> checkouts = libctrl.viewCheckOutItems();
				if(checkouts == null){
					System.out.println("Operation Failed.");
				}
				for(Checkout co:checkouts){
					Item it = co.getItem();
					System.out.println("Checkout id: "+co.getId()+"\t Customer id: "+co.getCustomerId()+"\t Due: "+co.getDueDate()+"\t "+it.getTypeString()+": "+it.getName());
				}
			}
			else if(in.equals("15")) //exit
			{
				break;
			}
			else
			{
				System.out.println("Please enter correct input.");
			}
			System.out.print("Continue? (y/n) ");
			in = input.nextLine();
			if(!in.toLowerCase().contains("y"))break;
		}
		System.out.println("Have a good day! Let me know if you need some lip therapy.");
	}

}
