package iteration1;

public class Book implements Item{
	private String code;
	private String name;
	private int type = Item.BOOK;
	private String typeString = "Book";
	private int quantity;
	private int avail;
	
	public Book(String name, String code, int quantity, int avail){
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.avail = avail;
	}
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getType() {
		return type;
	}
	
	@Override
	public String getTypeString() {
		return typeString;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
	@Override
	public void setCode() {
		// TODO Auto-generated method stub
	}
	@Override
	public void setName() {
		// TODO Auto-generated method stub
	}
	@Override
	public void setType() {
		// TODO Auto-generated method stub
	}
	@Override
	public void setQuantity() {
		// TODO Auto-generated method stub
	}
	@Override
	public int getAvail() {
		return avail;
	}
}
