package iteration3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class DatabaseSupport {
	
	
	private String query(String command){
		//System.out.println("Query: "+command);//debug
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
	
	private ArrayList<Item> sortItemsByName(ArrayList<Item> items){
		
		Collections.sort(items, new Comparator<Item>() {

			@Override
			public int compare(Item lhs, Item rhs) {
                int count = 0;
				if ((lhs.getTypeString()+lhs.getName()).compareTo(rhs.getTypeString()+rhs.getName()) > 0)
					return 1;
				else if ((lhs.getTypeString()+lhs.getName()).compareTo(rhs.getTypeString()+rhs.getName()) < 0)
					return -1;
				return 0;
			}
		});
		
		return items;
	}
	
	private Item assembleItem(JSONObject jobj){
		Item item = null;
		
		try{
		String name = jobj.getString("name");
		int type = jobj.getInt("type");
		String genre = jobj.getString("genre");
		String code = jobj.getString("code");
		int quantity = jobj.getInt("quantity");
		int avail = jobj.getInt("avail");
		
		if(type==Item.BOOK)			item = new Book(name, genre, code,quantity, avail);
		else if(type==Item.MOVIE)	item = new Movie(name,genre, code,quantity, avail);
		else if(type==Item.MUSIC)	item = new Music(name,genre, code,quantity, avail);
		
		return item;
		}catch(Exception e){}
		return item;
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
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				itemList.add(assembleItem(jobj));
			}
		} catch (JSONException e) {return null;}
		sortItemsByName(itemList);
		inventory.setItemList(itemList);
		return inventory;
	}
	
	Item getItem(String inCode){
		
		String query = "SELECT * FROM `items` WHERE code = '"+ inCode + "'";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			JSONObject jobj = jArr.getJSONObject(0);
				
			return assembleItem(jobj);
		} catch (JSONException e) {return null;}
	}
	
	boolean putInventoryItem(Item i){
		
		String query = "SELECT * FROM `items` WHERE code = '"+ i.getCode() + "'";
		String result = query(query);

		if (!result.equals("")){
			query = "DELETE FROM `items` WHERE code = '"+i.getCode()+"'";
			result = query(query);
		}
		
		query = "INSERT INTO `items` (`id`, `name`, `type`, `genre`, `code`, `quantity`, `avail`) "
				+ "VALUES (NULL, '"+i.getName()+"', '"+i.getType()+"', '"+i.getGenre()+"', '"+
				i.getCode()+"', "+i.getQuantity()+", "+i.getAvail()+")";
		result = query(query);
		
		System.out.println(result);
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
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				quantity = jobj.getInt("quantity");
				avail = jobj.getInt("avail");
			}
		} catch (JSONException e) {return false;}
		if(avail-toRemove <0)return false;

		query = "UPDATE `items` SET `quantity` = '"+(quantity-toRemove)+"', `avail` = '"+
				(avail-toRemove)+"' WHERE `code` = "+code;
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
			
		} catch (JSONException e) {return null;}
		
		return c;
	}

	public boolean putCheckout(Checkout c){
		
		String query = "DELETE FROM `arlenb_coms362db`.`checkouts` WHERE `checkouts`.`id` = '"+c.getId()+"'";
		String result = query(query);
		
		query = "INSERT INTO `arlenb_coms362db`.`checkouts` "+
				"(`id`, `cust_id`, `item_code`, `date_due`) VALUES ('"+
				c.getId()+"', '"+c.getCustomerId()+"', '"+
				c.getItem().getCode()+"', '"+c.getDueDate()+"');";
		result = query(query);
		
		if (result.equals("")){
			c.getItem().setAvail(c.getItem().getAvail()-1);
			putInventoryItem(c.getItem());
			return true;// successful update query returns exactly nothing.
		}	
		return false;
	}
	
	public boolean returnCheckout(int id){

		Checkout co = getCheckout(id);
						
		if(co==null){
			System.out.println("Checkout does not exist...");
			return false;
		}
				
		String query = "DELETE FROM `arlenb_coms362db`.`checkouts` WHERE `checkouts`.`id` = '"+id+"'";
		String result = query(query);
				
		if(result.equals("")){
			co.getItem().setAvail(co.getItem().getAvail()+1);
			putInventoryItem(co.getItem());
			return true;
		}
		
		return false;
	}

	public ArrayList<Checkout> getCheckOuts(){
		
		ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
		String query = "SELECT * FROM `checkouts`";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
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
	
	ArrayList<Item> searchByTitle(String title){
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items` WHERE name LIKE '%"+title+"%'";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				itemList.add(assembleItem(jobj));
			}
		} catch (JSONException e) {return null;}
		
		return itemList;
	}
	
	ArrayList<Item> searchByType(int term){
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items` WHERE type = "+term+"";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				itemList.add(assembleItem(jobj));
			}
		} catch (JSONException e) {return null;}
		
		return itemList;
	}
	
	ArrayList<Item> searchByGenre(String genre){
		//TODO add genre to database and 
		ArrayList<Item> itemList = new ArrayList<Item>();
		
		String query = "SELECT * FROM `items` WHERE genre LIKE '%"+genre+"%'";

		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				itemList.add(assembleItem(jobj));
			}
		} catch (JSONException e) {return null;}
		
		return itemList;
	}

	public ArrayList<Review> viewReviews(String itemCode) {
		ArrayList<Review> reviewList = new ArrayList<Review>();
		
		String query = "SELECT * FROM `reviews` WHERE item_code = '"+itemCode+"'";
		String result = query(query);
		
		if (result.equals(""))return null;// result was empty. return null object.
		
		try {
			JSONArray jArr = new JSONArray(result);
			for(int i=0 ; i<jArr.length(); i++){
				JSONObject jobj = jArr.getJSONObject(i);
				Review review = new Review(
						jobj.getInt("id"), jobj.getString("item_code"), jobj.getString("review"));
				reviewList.add(review);
			}
		} catch (JSONException e) {return null;}
		
		return reviewList;
	}

	public boolean addReview(String itemCode, String review) {
		
		String query = "SELECT * FROM `reviews` WHERE item_code = '"+itemCode+"'";
		String result = query(query);
		
		if (result.equals(""))return false;
		
		query = "INSERT INTO `reviews` (`id`, `item_code`, `review`) "
				+ "VALUES (NULL, '"+itemCode+"', '"+review+"');";
		result = query(query);
		
		if(result.equals(""))return true;//successful insert query returns exactly nothing.
		
		return true;
	}

	public boolean deleteReview(String id) {
		
		String query = "SELECT * FROM `reviews` WHERE id = "+id+"";
		String result = query(query);
		
		if (result.equals(""))return false;
		
		query = "DELETE FROM `reviews` WHERE id = "+id+"";
		result = query(query);
		if (result.equals(""))return true;// result was empty. return null object.
		
		return false;
	}
}
