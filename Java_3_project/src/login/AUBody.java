package login;

/**
* AUBody
*/
public class AUBody {
   private String fname;
   private String lname;
   private String auUsername;
   private String password;
   private String email;
   private String registered_date;

   public AUBody () {}
   public AUBody (String fname,String lname, String auU,String password,String email,String rd) {
       this.fname = fname;
       this.lname = lname;
       this.auUsername = auU;
       this.password = password;
       this.email = email;
       this.registered_date = rd;
   }
   public AUBody (AUBody au) {
       au.fname = fname;
       au.lname = lname;
       au.auUsername = auUsername;
       au.password = password;
       au.email = email;
       au.registered_date = registered_date;
   }
   //missing more setters and getters
   public void setFname(String fname) {this.fname = fname;}
   public String getFname() {
		return fname;
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
