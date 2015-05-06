package iteration3;

public class Librarian {

	private String pass;
	private String username;
	public Librarian(String username, String pass){
		this.pass = pass;
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
