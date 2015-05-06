package iteration3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Checkout 
{
	private int custId;
	private int id;
	private Item item;
	private String date;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public Checkout(int custId, Item item)
	{
		this.custId = custId;
		this.item = item;
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 2);  // number of days to add
		date = dateFormat.format(c.getTime());
	}
	
	public int getCustomerId(){
		return custId;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Item getItem(){
		return item;
	}
	
	public String getDueDate(){
		return date;
	}
	
	public void setDueDate(String newDate){
		date=newDate;
	}

	public void renew() {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(dateFormat.parse(date));
		} catch (ParseException e) {e.printStackTrace();}
		c.add(Calendar.DATE, 2);  // number of days to add
		date = dateFormat.format(c.getTime());
		System.out.println("New Return Date:"+date);
	}
}
