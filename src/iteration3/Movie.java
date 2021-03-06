package iteration3;

public class Movie implements Item{

	private String code;
	private String name;
	private int type = Item.MOVIE;
	private String typeString = "Movie";
	private int quantity;
	private int fine;
	private int avail;
	private String genre;
	
	public Movie(String name, String genre, String code, int quantity, int avail){
		this.code = code;
		this.name = name;
		this.genre = genre;
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
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public boolean increaseQuantity(int amount) {
		if(amount < 0)
			return false;
		
		this.quantity += amount;
		this.avail += amount;
		return true;
	}
	@Override
	public int getAvail() {
		return avail;
	}
	@Override
	public void setAvail(int nowAvail) {
		avail=nowAvail;
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
	@Override
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}

}
