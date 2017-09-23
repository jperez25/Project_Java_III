package login.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.AUBody;

public class RegisterController {
	// called by the FXML loader after the labels declared below are injected:
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField auUserName;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField pass1;
	@FXML
	private TextField email;
	@FXML
	private Button regBtn;
	@FXML
	private Button backBtn;
	@FXML
	private Label fnameStar;
	@FXML
	private Label lnameStar;
	@FXML
	private Label auUserStar;
	@FXML
	private Label passwordStar;
	@FXML
	private Label pass2Star;
	@FXML
	private Label emailStar;
	 
	public void initialize() throws Exception {
		
		try{  
			 Class.forName("com.mysql.jdbc.Driver");
			 }catch(Exception e){ 
				 System.out.println(e);}
		
		 //Getting Today's day
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dt = new Date();
		
		//Our object
		AUBody body = new AUBody();
		System.out.println(body);
		regBtn.setOnAction(e->{
			
			//Set starts to false every time user click button
			fnameStar.setVisible(false);
			lnameStar.setVisible(false);
			auUserStar.setVisible(false);
			passwordStar.setVisible(false);
			pass2Star.setVisible(false);
			emailStar.setVisible(false);
			
			//If any of the fields is empty rise alert 
			if (fname.getText().equals("") || lname.getText().equals("") || auUserName.getText().equals("") || pass.getText().equals("") || email.getText().equals("") || pass1.getText().equals("")) {
				//Alert User of missing fields
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Message");
			    alert.setHeaderText("Empty Fields");
			    alert.setContentText("Some fileds might be empty, please fill the missing fields");
			    alert.showAndWait();
			    
			    //Check every field individually
			    if (fname.getText().equals("")) {
					fnameStar.setVisible(true);
				}
			    if (lname.getText().equals("")) {
					lnameStar.setVisible(true);
				}
			    if (auUserName.getText().equals("")) {
					auUserStar.setVisible(true);
				}
			    if (pass.getText().equals("")) {
					passwordStar.setVisible(true);
				}
			    if (pass1.getText().equals("")) {
					pass2Star.setVisible(true);
				}
			    if (email.getText().equals("")) {
					emailStar.setVisible(true);
				}
			    
			}
			//If everything is right set object data fields
			else {
				//If passwords are equal
				if (pass.getText().equals(pass1.getText())) {
					
					ResultSet rs;
					//Connect to data base
					try {
						//Connect
						Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/whereismyspot","root","Computer1");
						//Statement
						Statement stmt=con.createStatement();  
						//Query
						rs = stmt.executeQuery("select UserName from user where UserName='"+auUserName.getText()+"';");
						boolean isEmpty = rs.next();
						
						
						//If user name is not found in data base
						if (!isEmpty) {
							//Write data to DB
							String val = "'"+fname.getText()+"','"+lname.getText()+"','"+auUserName.getText()+"','"+pass.getText()+"','"+email.getText()+"'";
							int update = stmt.executeUpdate("insert into user (First_Name, Last_Name, UserName, Password, Email) values ("+val+");");
							
							//Switch screens
							FXMLLoader loader2 = new FXMLLoader(getClass().getResource("..\\Screens\\First_Login.fxml"));
					        Parent root2;
							try {
								root2 = loader2.load();
						        Scene scene = new Scene(root2);
								Stage stage = (Stage) backBtn.getScene().getWindow(); 
								stage.setScene(scene);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							//Close connection
							con.close();
						}
						//Else if block might not be needed it
						else if (auUserName.getText().equals(rs.getString(1))) {
							auUserStar.setVisible(true);
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
						    alert.setTitle("Alert!");
						    alert.setHeaderText("Username in use");
						    alert.setContentText("The user name you entered is already in use, \n please choose another user name");
						    alert.showAndWait();
						}
						
						//User name was found and cannot continue
						else {
							auUserStar.setVisible(true);
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
						    alert.setTitle("Alert!");
						    alert.setHeaderText("Username in use");
						    alert.setContentText("The user name you entered is already in use, \n please choose another user name");
						    alert.showAndWait();
						}
						
					}catch(Exception a) {a.printStackTrace();}
				}
				//If the passwords dont match
				else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
				    alert.setTitle("Message");
				    alert.setHeaderText("Passwords");
				    alert.setContentText("Passwords entered do not match");
				    alert.showAndWait();
					passwordStar.setVisible(true);
					pass2Star.setVisible(true);
				}
			}
		});
		//Go back to the main screen
		backBtn.setOnAction(e->{
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\Screens\\First_login.fxml"));
	        Parent root;
			try {
				root = loader.load();
		        Scene scene = new Scene(root);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
}/*
body.setFname(fname.getText());
body.setLname(lname.getText());
body.setAuUsername(auUserName.getText());
body.setPassword(pass.getText());
body.setEmail(email.getText());
body.setRegistered_date(dateFormat.format(dt));
System.out.println("Successful");
*/