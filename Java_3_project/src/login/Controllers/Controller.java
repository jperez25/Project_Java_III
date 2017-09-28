package login.Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//imports...

public class Controller {
//Fxml injections
 @FXML
 private Button bt1 ;
 @FXML
 private TextField txtfl;
 @FXML
 private PasswordField passfl;
 @FXML
 private Hyperlink lnk1;
 @FXML
 private Hyperlink lnk2;
 @FXML
 private Label userStar;
 @FXML
 private Label passStar;

 // called by the FXML loader after the labels declared above are injected:
	 public void initialize() throws Exception {
		 //Locally working with data bases 
		/*HashMap<String, String> map = new HashMap<String, String>();
		 map.put("jojo","123456");*/
		 
		 
	     // do initialization and configuration work...
	
	     // When send button is clicked it should grant access to the app
		bt1.setOnAction(e->{
			userStar.setVisible(false);
			passStar.setVisible(false);
			 String username = txtfl.getText();
			 String pass = passfl.getText();
			 
			 //Check empty fields
			 if (username.equals("") || pass.equals("")) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
				    alert.setTitle("Message");
				    alert.setHeaderText("Empty Fields");
				    alert.setContentText("The username and password fields might be empty, try again");
				    alert.showAndWait();
				    
				    if (username.equals("")) {
				    	userStar.setVisible(true);
					}
				    if (pass.equals("")) {
				    	passStar.setVisible(true);
					} 
			 }
			 
			 else {
				 try{ 
					 Class.forName("com.mysql.jdbc.Driver");  
					 Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/whereismyspot","root","Computer1");  
					 Statement stmt=con.createStatement();
					 //If user not found returns null
					 ResultSet userName = stmt.executeQuery("select UserName from user where UserName='"+username+"';");
					 boolean isEmpty = userName.next();
					 //System.out.println(userName.next());
					 
					 //If user is not found
					 if (!isEmpty) {
						 Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Message");
							alert.setHeaderText("User not found");
							alert.setContentText("The user name you have entered was not found in our data base");
							alert.showAndWait();
						 
					 }
					 //User is found
					 else {
						//If password does not match registers
						 ResultSet passW = stmt.executeQuery("select Password from user where Password='"+pass+"' and UserName='"+username+"';");
						 boolean isEmptyPass = passW.next();
						 if (!isEmptyPass) {
							 Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Message");
								alert.setHeaderText("Wrong input");
								alert.setContentText("User name or password might be wrong");
								alert.showAndWait();
						 }
						 else {
							//switch scenes
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Message");
								alert.setHeaderText("Sucessful Login");
								alert.setContentText("User has log in successfully");
								alert.showAndWait();
								    
								//go to pick a lot
								 FXMLLoader loader2 = new FXMLLoader(getClass().getResource("..\\Screens\\ParkingLotsScreen.fxml"));
							     Parent root2;
							     try {
										root2 = loader2.load();
								        Scene scene = new Scene(root2);
										Stage stage = (Stage) bt1.getScene().getWindow(); 
										stage.setScene(scene);
								} 
							     catch (IOException e1) {
										System.out.println(e);
								}
						}
					 }
				}
				 catch(Exception ex) {ex.printStackTrace();}
			 }//End of else block
		 }//end of event handler
		);
		lnk1.setOnAction(e->{
			//Go to register Scene
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("..\\Screens\\RegisterScene.fxml"));
	        Parent root2;
			try {
				root2 = loader2.load();
		        Scene scene = new Scene(root2);
				Stage stage = (Stage) bt1.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
		lnk2.setOnAction(e->{
			//Go to Forgot my password Screen
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("..\\Screens\\ForgotPassword.fxml"));
	        Parent root2;
			try {
				root2 = loader2.load();
		        Scene scene = new Scene(root2);
				Stage stage = (Stage) bt1.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
		txtfl.requestFocus();
		passfl.requestFocus();
		bt1.requestFocus();
	}
}