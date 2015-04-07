package iteration1;

public class Music implements Item{
	private String code;
	private String name;
	private int type = Item.MUSIC;
	private String typeString = "Music";
	private int quantity;
	private int fine;
	private int avail;
	
	public Music(String name, String code, int quantity, int avail){
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
	@Override
	public void modifyFine(int fine) {
		this.fine += fine;
		
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
}
