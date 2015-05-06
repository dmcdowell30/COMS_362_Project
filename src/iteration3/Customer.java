package iteration3;

public class Customer {

	private String name;
	private int id;
	private int fines;
	
	public Customer(String name){
		this.setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFines() {
		return fines;
	}

	public void setFines(int fines) {
		this.fines = fines;
	}
	public void payFine(int fine){
		if(fine > fines){
			fines = 0;
		}
		else{
			fines -= fine;
		}
	}
	public void addFine(int fine){
		fines += fine;
	}
}
