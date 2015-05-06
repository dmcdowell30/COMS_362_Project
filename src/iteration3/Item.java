package iteration3;

public interface Item {

	int BOOK = 0;
	int MOVIE = 1;
	int MUSIC = 2;
	
	public String getCode();
	public String getName();
	public int getType();
	public String getTypeString();
	public int getQuantity();
	public void modifyFine(int fine);
	public void setCode(String code);
	public void setName(String name);
	public void setType(int type);
	public boolean increaseQuantity(int amount);
	public int getAvail();
	public void setAvail(int nowAvail);
	public String getGenre();

}
