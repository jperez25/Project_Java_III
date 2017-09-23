package login.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import login.AUBody;

public class ForgotController {
	@FXML
	private Button backBtn;
	@FXML
	private Button sendBtn;
	@FXML
	private TextField userfl;
	@FXML
	private TextField emailfl;
	@FXML
	private Label userStar;
	@FXML
	private Label emailStar;
	@FXML
	private AnchorPane codePane;
	@FXML
	private AnchorPane ogPane;
	@FXML
	private TextField resTxtFl;
	@FXML
	private Button verifyBtn;
	
	
	public void initialize() throws Exception {
		//Setting an email
		/*
		AUBody body = new AUBody(); 
		body.setEmail("jojo@aumail.edu");
		body.setAuUsername("jojo");
		HashMap<String, AUBody> map = new HashMap<String, AUBody>();
		 map.put("jojo", body);*/

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
		
		//Check user and email
		//send email with recovery code
		sendBtn.setOnAction(e->{
			//set stars to false
			userStar.setVisible(false);
			emailStar.setVisible(false);
			
			 String username = userfl.getText();
			 String email = emailfl.getText();
			 
				 
			if (username.equals("") || email.equals("")) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
					 alert.setTitle("Alert!");
					 alert.setHeaderText("Empty Fields");
					 alert.setContentText("Fields might be empty");
					 alert.showAndWait();
					    
				if (username.equals("")) {
					    	userStar.setVisible(true);
					}
				if (email.equals("")) {
					    	emailStar.setVisible(true);
				}
			}
			else {
					 try{ 
						 //Connect to DB
						 Class.forName("com.mysql.jdbc.Driver");  
						 //Establish connection
						 Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/whereismyspot","root","Computer1");  
						 //Create Statement
						 Statement stmt=con.createStatement();
						 //If user not found returns null
						 ResultSet userName = stmt.executeQuery("select UserName from user where UserName='"+username+"';");
						 boolean isEmpty = userName.next();
						 
						 if (!isEmpty) {
							Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
							alert1.setTitle("Message");
							alert1.setHeaderText("User not found");
							alert1.setContentText("The user name you have entered was not found in our data base");
							alert1.showAndWait();
						}
						 else {
							ResultSet eMail = stmt.executeQuery("select Email from user where Email='"+email+"';");
							boolean isEmailEmpty = eMail.next();
							if (!isEmailEmpty) {
								Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
								alert1.setTitle("Message");
								alert1.setHeaderText("Email address not found");
								alert1.setContentText("The user name and email does not match our registers");
								alert1.showAndWait();
							}
							else {
								System.out.println("Code should be send");
								System.out.println("Call method to send email");
								
								ogPane.setVisible(false);
								codePane.setLayoutY(ogPane.getLayoutY());
								codePane.setLayoutX(ogPane.getLayoutX());
								codePane.setVisible(true);
							}
						}
					 }
					 catch(Exception a) {a.printStackTrace();}
			}
			 
			
		});
		//Check code and compare with the code send to user
		verifyBtn.setOnAction(e->{
			 String rescode = resTxtFl.getText();
			 
		});
	}

}