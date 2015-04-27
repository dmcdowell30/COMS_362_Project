package iteration1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class DatabaseSupport {
	
	
	private String query(String command){
		//System.out.println("Query: "+command);//TODO debug
		String result = "";
		String charset = "UTF-8";
		String url = "http://databasesupport.arlenburroughs.com/db_query2.php";
		String query = "";
		
		URLConnection connection;
		String contentType = null;
		InputStream response = null;
		try {
			query = "?query="+URLEncoder.encode(command, "UTF-8");
			connection = new URL(url+query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			response = connection.getInputStream();
			
			contentType = connection.getHeaderField("Content-Type");
		} catch (IOException e1) {
			System.out.println("Error1");
			e1.printStackTrace();
		}

		if (charset != null) {
		    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, charset))) {
		        for (String line; (line = reader.readLine()) != null;) {
		            result = result+line;
		        }
		    } catch (IOException e) {
		    	System.out.println("Error2");
				e.printStackTrace();
			}
		}

		//System.out.println("From DB: "+result);
		return result;
	}
	
	
	/////////////////////////////////////
	////// Below are from Class Diagram 
	/////////////////////////////////////
	
	boolean putLibrarian(Librarian l){
		
		// determine if a current librarian exists with that username
		String query = "SELECT * FROM `librarians` WHERE username = '"+l.getUsername()+"'";
		String result = query(query);
		
		if(!result.equals("")){
			return false;//result wasn't empty. A librarian exists, or db error.
		}
		
		
		query = "INSERT INTO `librarians` (`id`, `username`, `password`) "
				+ "VALUES (NULL, '"+l.getUsername()+"', '"+l.getPass()+"');";
		result = query(query);
		
		if(result.equals(""))return true;//successful insert query returns exactly nothing.
		
		return true;
	}
	
	boolean removeLibrarian(String username){
		
		// determine if the username exists.
		String query = "SELECT * FROM `librarians` WHERE username = '"+username+"'";
		String result = query(query);
				
		if(result.equals("")){
			System.out.println("Librarian does not exist...");
			return false;
		}
		
		query = "DELETE FROM `arlenb_coms362db`.`librarians` WHERE `librarians`.`username` = '"+username+"'";
		result = query(query);
		
		if(result.equals(""))return true;//successful delete query returns exactly nothing.
		
		return false;
	}
	
	boolean putCustomer(Customer c){
		removeCustomer(c.getId());
		int id = c.getId();

		String query = "INSERT INTO `customers` (`id`, `name`, `fine`) "
				+ "VALUES ('"+id+"', '"+c.getName()+"', '"+c.getFines()+"') "
						+ " ON DUPLICATE KEY UPDATE id = '"+c.getId()+"'";
		String result = query(query);
		
		if(result.equals(""))return true;//successful insert query returns exactly nothing.
		
		return false;
	}
	
	boolean removeCustomer(int id){
		// determine if the username exists.
		String query = "SELECT * FROM `customers` WHERE id = "+ id;
		String result = query(query);

		if (result.equals("")){
			System.out.println("Customer does not exist...");
			return false;
		}

		query = "DELETE FROM `customers` WHERE `id` = "+id;
		result = query(query);

		if (result.equals(""))return true;// successful delete query returns exactly nothing.

		return false;
	}
	
	Customer getCustomer(int id){
		Customer customer = null;
		
		String query = "SELECT * FROM `customers` WHERE id = "+ id;
		String result = query(query);
		
		if (result.equals(""))return customer;// result was empty. return null object.
		try {
			JSONArray jArr = new JSONArray(result);
			JSONObject jobj = jArr.getJSONObject(0);
			customer = new Customer(jobj.getString("name"));
			customer.setId(Integer.parseInt(jobj.getString("id")));
			customer.setFines(Integer.parseInt(jobj.getString("fine")));
		} catch (JSONException e) {e.printStackTrace();}
		
		
		return customer;
	}
	
	ArrayList<Customer> getCustomerList(){
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		String query = "SELECT * FROM `customers`";
		String result = query(query);
		
		if (result.equals(""))return customerList;// result was empty. return null object.
		try {
			JSONArray jArr = new JSONArray(result);
			Customer aCustomer;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				aCustomer = new Customer(jobj.getString("name"));
				aCustomer.setId(Integer.parseInt(jobj.getString("id")));
				aCustomer.setFines(Integer.parseInt(jobj.getString("fine")));
				customerList.add(aCustomer);
			}
		} catch (JSONException e) {e.printStackTrace();}
		
		return customerList;
	}
	
	Inventory RequestInventory(){
		
		Inventory inventory = new Inventory();
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items`";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				String name = jobj.getString("name");
				int type = jobj.getInt("type");
				String code = jobj.getString("code");
				int quantity = jobj.getInt("quantity");
				int avail = jobj.getInt("avail");
				
				if(type==Item.BOOK)			anItem = new Book(name,code,quantity, avail);
				else if(type==Item.MOVIE)	anItem = new Movie(name,code,quantity, avail);
				else if(type==Item.MUSIC)	anItem = new Music(name,code,quantity, avail);
				
				itemList.add(anItem);
			}
		} catch (JSONException e) {return null;}
		
		inventory.setItemList(itemList);
		return inventory;
	}
	
	Item getItem(String inCode){
		
		String query = "SELECT * FROM `items` WHERE code = '"+ inCode + "'";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				String name = jobj.getString("name");
				int type = jobj.getInt("type");
				String code = jobj.getString("code");
				int quantity = jobj.getInt("quantity");
				int avail = jobj.getInt("avail");
				
				if(type==Item.BOOK)			anItem = new Book(name,code,quantity, avail);
				else if(type==Item.MOVIE)	anItem = new Movie(name,code,quantity, avail);
				else if(type==Item.MUSIC)	anItem = new Music(name,code,quantity, avail);
				
				return anItem;
			}
		} catch (JSONException e) {return null;}
		
		return null;
	}
	
	boolean putInventoryItem(Item i){
		
		String query = "SELECT * FROM `items` WHERE code = '"+ i.getCode() + "'";
		String result = query(query);

		if (!result.equals(""))
			return false;// result wasn't empty. Item with same code already exists.
		
		query = "INSERT INTO `items` (`id`, `name`, `type`, `code`, `quantity`, `avail`) "
				+ "VALUES (NULL, '"+i.getName()+"', '"+i.getType()+"', '"+i.getCode()+"', '"+i.getQuantity()+"', '"+i.getAvail()+"')";
		result = query(query);
		
		if(result.equals(""))return true;
		
		return false;
	}
	
	boolean removeInventoryItem(String code, int toRemove){
		// determine if the item exists.
		String query = "SELECT * FROM `items` WHERE code = '"+code+"'";
		String result = query(query);

		if (result.equals(""))
			return false;// result was empty. no item exists for code
		
		int quantity =0;
		int avail = 0;
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				quantity = jobj.getInt("quantity");
				avail = jobj.getInt("avail");
			}
		} catch (JSONException e) {return false;}
		if(avail-toRemove <0)return false;

		query = "UPDATE `items` SET `quantity` = '"+(quantity-toRemove)+"', `avail` = '"+(avail-toRemove)+"' WHERE `code` = "+code;
		result = query(query);

		if (result.equals(""))
			return true;// successful update query returns exactly nothing.
		
		return false;
	}


	public Checkout getCheckout(int id){
		
		String query = "SELECT * FROM `checkouts` WHERE id = '"+ id + "'";
		String result = query(query);
		
		Checkout c = null;
		try {
			JSONArray jArr = new JSONArray(result);
			JSONObject jobj = jArr.getJSONObject(0);
			int idBack = jobj.getInt("id");
			int custId = jobj.getInt("cust_id");
			String code = jobj.getString("item_code");
			String date = jobj.getString("date_due");

			Item item = getItem(code);
			c = new Checkout(custId, item);
			c.setId(idBack);
			c.setDueDate(date);
			
			returnCheckout(idBack);
			
		} catch (JSONException e) {return null;}
		
		return c;
	}

	public boolean putCheckout(Checkout c){
		String query = "INSERT INTO `arlenb_coms362db`.`checkouts` (`id`, `cust_id`, `item_code`, `date_due`) "
				+ "VALUES ('"+c.getId()+"', '"+c.getCustomerId()+"', '"+c.getItem().getCode()+"', '"+c.getDueDate()+"');";
		String result = query(query);
		
		if (result.equals(""))
			return true;// successful update query returns exactly nothing.
		return false;
	}
	
	ArrayList<Item> searchByTitle(String title){
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items` WHERE code = '"+title+"'";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				String name = jobj.getString("name");
				int type = jobj.getInt("type");
				String code = jobj.getString("code");
				int quantity = jobj.getInt("quantity");
				int avail = jobj.getInt("avail");
				
				if(type==Item.BOOK)			anItem = new Book(name,code,quantity, avail);
				else if(type==Item.MOVIE)	anItem = new Movie(name,code,quantity, avail);
				else if(type==Item.MUSIC)	anItem = new Music(name,code,quantity, avail);
				
				itemList.add(anItem);
			}
		} catch (JSONException e) {return null;}
		
		return itemList;
	}
	
	ArrayList<Item> searchByType(String term){
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items` WHERE code = '"+term+"'";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				String name = jobj.getString("name");
				int type = jobj.getInt("type");
				String code = jobj.getString("code");
				int quantity = jobj.getInt("quantity");
				int avail = jobj.getInt("avail");
				
				if(type==Item.BOOK)			anItem = new Book(name,code,quantity, avail);
				else if(type==Item.MOVIE)	anItem = new Movie(name,code,quantity, avail);
				else if(type==Item.MUSIC)	anItem = new Music(name,code,quantity, avail);
				
				itemList.add(anItem);
			}
		} catch (JSONException e) {return null;}
		
		return itemList;
		
	}
	
	ArrayList<Item> searchByGenre(String genre){
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items` WHERE code = '"+genre+"'";

		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				String name = jobj.getString("name");
				int type = jobj.getInt("type");
				String code = jobj.getString("code");
				int quantity = jobj.getInt("quantity");
				int avail = jobj.getInt("avail");
				
				if(type==Item.BOOK)			anItem = new Book(name,code,quantity, avail);
				else if(type==Item.MOVIE)	anItem = new Movie(name,code,quantity, avail);
				else if(type==Item.MUSIC)	anItem = new Music(name,code,quantity, avail);
				
				itemList.add(anItem);
			}
		} catch (JSONException e) {return null;}
		
		return itemList;
		
	}
	public boolean returnCheckout(int id){

		// determine if the checkout exists.
		String query = "SELECT * FROM `checkouts` WHERE id = '"+id+"'";
		String result = query(query);
						
		if(result.equals("")){
			System.out.println("Checkout does not exist...");
			return false;
		}
				
		query = "DELETE FROM `arlenb_coms362db`.`checkouts` WHERE `checkouts`.`id` = '"+id+"'";
		result = query(query);
				
		if(result.equals(""))return true;
		
		return false;
	}

	public ArrayList<Checkout> getCheckOuts(){
		
		ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
		
		String query = "SELECT * FROM `checkouts`";

		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			Item anItem = null;
	
			Checkout c = null;
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				int id = jobj.getInt("id");
				int custId = jobj.getInt("cust_id");
				String code = jobj.getString("item_code");
				String date = jobj.getString("date_due");
				
				Item item = getItem(code);
				c = new Checkout(custId, item);
				c.setId(id);
				c.setDueDate(date);
				checkouts.add(c);
			}
		} catch (JSONException e) {return null;}
		
		return checkouts;
	}

}
