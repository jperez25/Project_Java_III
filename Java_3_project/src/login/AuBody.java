package login;
public class AuBody {
	private String fname;
	private String lname;
	private String auUsername;
	private String password;
	private String email;
	private String registered_date;
	
	public AuBody() {
		
	};
	
	public AuBody(String fname, String lname, String auUsername, String password, String email, String registered_date) {
		
		this.fname = fname;
		this.lname = lname;
		this.auUsername = auUsername;
		this.password = password;
		this.email = email;
		this.registered_date = registered_date;
	}
	
	public AuBody (AuBody au) {
	       au.fname = fname;
	       au.lname = lname;
	       au.auUsername = auUsername;
	       au.password = password;
	       au.email = email;
	       au.registered_date = registered_date;
	   }

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAuUsername() {
		return auUsername;
	}

	public void setAuUsername(String auUsername) {
		this.auUsername = auUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistered_date() {
		return registered_date;
	}

	public void setRegistered_date(String registered_date) {
		this.registered_date = registered_date;
	}
}
