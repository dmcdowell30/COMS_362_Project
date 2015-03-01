package iteration1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		LibraryController libctrl = new LibraryController();
		
		while(true)
		{
			System.out.println("What would you like to do?");
			System.out.println("1) Add something");
			System.out.println("2) Delete something");
			System.out.println("3) View a Customer");
			System.out.println("4) View Customer List");
			System.out.println("5) View Inventory");
			System.out.println("6) Exit");
			
			System.out.print("Choice: ");
			String in = input.nextLine();
			System.out.println();
			
			if(in.equals("1"))
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
					
					libctrl.addLibrarian(user, pass);
				}
				else if(in.equals("2"))
				{
					System.out.print("Enter username: ");
					String user = input.nextLine();
					
					libctrl.addCustomer(user);
				}
				else if(in.equals("3"))
				{
					System.out.print("Enter item code: ");
					String code = input.nextLine();

					System.out.print("Enter item name: ");
					String name = input.nextLine();

					System.out.print("Enter item type: ");
					String type = input.nextLine();

					System.out.print("Enter item quantity: ");
					String quan = input.nextLine();
					int quantity = Integer.parseInt(quan);
					
					
					libctrl.addInventoryItem(code, name, type, quantity);
				}
				else
				{
					System.out.println("Command not recognized. Exiting program...");
					break;
				}
				
			}
			else if(in.equals("2"))
			{
				System.out.println("What would you like to delete?");
				System.out.println("1) Librarian");
				System.out.println("2) Customer");
				System.out.println("3) Inventory Item");
				System.out.println("Choice: ");
				
	
				in = input.nextLine();
				
				if(in.equals("1"))
				{
					System.out.print("Enter username: ");
					String user = input.nextLine();
					
					libctrl.deleteLibrarian(user);
				}
				else if(in.equals("2"))
				{
					System.out.print("Enter customer id: ");
					String id = input.nextLine();
					
					libctrl.deleteCustomer(id);
				}
				else if(in.equals("3"))
				{
					System.out.print("Enter item code: ");
					String code = input.nextLine();

					System.out.print("Enter item quantity: ");
					String quan = input.nextLine();
					int quantity = Integer.parseInt(quan);
					
					
					libctrl.addInventoryItem(code, quantity);
				}
				else
				{
					System.out.println("Command not recognized. Exiting program...");
					break;
				}
			}
			else if(in.equals("3"))
			{
				System.out.print("Enter user id: ");
				String user = input.nextLine();
				int id = Integer.parseInt(user);
				
				libctrl.viewCustomer(id);
			}
			else if(in.equals("4"))
			{
				libctrl.viewCustomerList();
			}
			else if(in.equals("5"))
			{
				libctrl.viewInventory();
			}
			else if(in.equals("6"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter correct input.");
			}
		}
	}

}
