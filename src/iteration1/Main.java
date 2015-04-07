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
			System.out.println("6) Exit");
			
			System.out.print("Choice: ");
			String in = input.nextLine();
			System.out.println();
			
			boolean success;
			if(in.equals("1"))// add
			{
				System.out.println("What would you like to add?");
				System.out.println("1) Librarian");
				System.out.println("2) Customer");
				System.out.println("3) Inventory Item");
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
					System.out.println("Customer id: "+id+", name: "+cust.getName());
				}
			}
			else if(in.equals("4")) // view customer list
			{
				ArrayList<Customer> custList = libctrl.viewCustomerList();
				for(Customer customer: custList){
					System.out.println("Customer id: "+customer.getId()+", name: "+customer.getName());
				}
			}
			else if(in.equals("5")) // view inventory
			{
				Inventory inventory = libctrl.viewInventory();
				for(Item item:inventory.getInventory()){
					String title = "\""+item.getName()+"\"";
					while(title.length()<25)title = title+" ";
					System.out.println(item.getTypeString()+":\t"+title+"\t, code:"+item.getCode()+
							"\t quantity:"+item.getQuantity());
				}
			}
			else if(in.equals("6")) // exit
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
