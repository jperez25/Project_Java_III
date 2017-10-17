package login.Controllers;

import java.io.IOException;
import java.sql.ResultSet;
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
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import login.Connector;

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
	@FXML
	private Labeled errorLabel;
	
	public void initialize() throws Exception {
		
		//Getting Today's day
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = new Date();
		
		email.setOnKeyPressed(e->{
			if (e.getCode()== KeyCode.ENTER) {
				regBtn.fire();
			}
		});
		
		regBtn.setOnAction(e->{
			
			//Set starts to false every time user click button
			fnameStar.setVisible(false);
			lnameStar.setVisible(false);
			auUserStar.setVisible(false);
			passwordStar.setVisible(false);
			pass2Star.setVisible(false);
			emailStar.setVisible(false);
			errorLabel.setVisible(false);
			
			//If any of the fields is empty rise alert 
			if (fname.getText().equals("") || lname.getText().equals("") || auUserName.getText().equals("") ||
					pass.getText().equals("") || email.getText().equals("") || pass1.getText().equals("")) {
				
				//Alert User of missing fields
				errorLabel.setVisible(true);
			    
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
			//If all fields are filled
			else {
				//If passwords are equal
				if (pass.getText().equals(pass1.getText())) {
					try {
						//Connect to data base
						Connector con = new Connector(); 
						//Query
						ResultSet user = con.execQuery("select UserName from user where UserName='"+auUserName.getText()+"';");
						boolean isEmpty = user.next();
						
						
						//If user name is not found in data base
						if (!isEmpty) {
							//Write data to DB
							String val = "'"+fname.getText()+"','"+lname.getText()+"','"+auUserName.getText()+"','"+pass.getText()+"','"+email.getText()+"', '"+dateFormat.format(dt)+"'";
							con.execUpdate("insert into user (First_Name, Last_Name, UserName, Password, Email,Register_Date) values ("+val+");");
							
							//Switch screens
							FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Screens/First_Login.fxml"));
					        Parent root2;
							try {
								root2 = loader2.load();
						        Scene scene = new Scene(root2);
								Stage stage = (Stage) backBtn.getScene().getWindow(); 
								stage.setScene(scene);
							} 
							//Let user know screen was not found
							catch (IOException e1) {
								Alert alert = new Alert(Alert.AlertType.ERROR);
								alert.setTitle("Screen Error");
								alert.setHeaderText("Screen Not found");
								alert.setContentText("The screen was not found");
								alert.showAndWait();
							}
							//Close connection
							con.close();
						}						
						//User name was found and cannot continue
						else {
							auUserStar.setVisible(true);
							errorLabel.setText("UserName in use");
							errorLabel.setVisible(true);
						}
						
					}catch(Exception a) {
						a.printStackTrace();
						//Let user know something happened
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Connection Error");
						alert.setHeaderText("Connection Error");
						alert.setContentText("You are not connected to the internet");
						alert.showAndWait();
					}
				}
				//If the passwords don't match
				else {
					errorLabel.setText("Passwords Do not Match");
					errorLabel.setVisible(true);
					passwordStar.setVisible(true);
					pass2Star.setVisible(true);
				}
			}
		});
		//Go back to the main screen
		backBtn.setOnAction(e->{
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/First_login.fxml"));
	        Parent root;
			try {
				root = loader.load();
		        Scene scene = new Scene(root);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				//Let user know screen was not found
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});
	}
}