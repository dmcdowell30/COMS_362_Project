package iteration1;

public interface Item {

	int BOOK = 0;
	int MOVIE = 1;
	int MUSIC = 2;
	
	public String getCode();
	public String getName();
	public int getType();
	public String getTypeString();
	public int getQuantity();
	public void setCode();
	public void setName();
	public void setType();
	public void setQuantity();
}
