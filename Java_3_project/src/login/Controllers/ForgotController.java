package login.Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
		
		//listener
		emailfl.setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				sendBtn.fire();	
			}
		 });

		backBtn.setOnAction(e->{
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/First_login.fxml"));
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
		
		emailfl.setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				sendBtn.fire();	
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
							 final String user = "auparkinglot@gmail.com";
							 final String pass = "ParkingLot";
							 
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
								Properties props = new Properties();
								props.put("mail.smtp.auth", "true");
								props.put("mail.smtp.starttls.enable", "true");
								props.put("mail.smtp.host", "smtp.gmail.com");
								props.put("mail.smtp.port", "587");
								
								Session session = Session.getInstance(props, new javax.mail.Authenticator() {
									@Override
									protected PasswordAuthentication getPasswordAuthentication() {
										return new PasswordAuthentication(user, pass);
									}
								});
								
								try {
									ResultSet emailConfirm = stmt.executeQuery("select email from user where username='"+username+"';");
									emailConfirm.next();
									
									
									Message message = new MimeMessage(session);
									message.setFrom(new InternetAddress("auparkinglot@gmail.com")); //Hard coded who's sending the email
									message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailConfirm.getString(1))); //Hard coded who the email is being sent to
																												//Can use array or the database after being setup
									message.setSubject("My first email"); //Subject of the email
									ResultSet emailPass = stmt.executeQuery("select password from user where username='"+username+"';");
									emailPass.next();
									message.setContent("<h:body style=background-color:white;font-family:verdana; color:#002>" //Uses html for a basic setup for the email
											+ "You're resetting your email!<br/>Your email is: "+ emailPass.getString(1) + "<br/>" //add email password from database in here
											+ "</body>","text/html; charset=utf-8");
									Transport.send(message); //Sends the message to the user
									
									Alert alert = new Alert(Alert.AlertType.INFORMATION); //Notify user that email was sent to their email address
									alert.setTitle("Email sent");
									alert.setHeaderText("Email was sent");
									alert.setContentText("An email was sent to your account with your password.\n Try not to forget it this time.");
									alert.showAndWait();
									
									//Take user to login screen
									 FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Screens/First_Login.fxml"));
								     Parent root2;
								     try {
											root2 = loader2.load();
									        Scene scene = new Scene(root2);
											Stage stage = (Stage) backBtn.getScene().getWindow(); 
											stage.setScene(scene);
									} 
								     catch (IOException e1) {
											System.out.println(e);
									}
								} catch (MessagingException x) {
									throw new RuntimeException(x);
								}
								
								
							}
						}
					 }
					 catch(Exception a) {a.printStackTrace();}
					 }
			 
			
		});
		//Check code and compare with the code send to user
		verifyBtn.setOnAction(e->{
			ogPane.setVisible(false);
			codePane.setLayoutY(ogPane.getLayoutY());
			codePane.setLayoutX(ogPane.getLayoutX());
			codePane.setVisible(true);
			 
		});
		userfl.setFocusTraversable(true);
		emailfl.setFocusTraversable(true);
	}

}