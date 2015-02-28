package iteration1;

public class Book implements Item{
	private String code;
	private String name;
	private String type;
	private int quantity;
	
	public Book(String code, String name, String type, int quantity){
		this.code = code;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
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
	public String getType() {
		return type;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
	@Override
	public String setCode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String setName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String setType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int setQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}
}
