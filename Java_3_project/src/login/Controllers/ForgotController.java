package login.Controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	
	
	
	@SuppressWarnings("unlikely-arg-type")
	public void initialize() throws Exception {
		//Setting an email
		AUBody body = new AUBody(); 
		body.setEmail("jojo@aumail.edu");
		body.setAuUsername("jojo");
		HashMap<String, AUBody> map = new HashMap<String, AUBody>();
		 map.put("jojo", body);

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
			
			userStar.setVisible(false);
			emailStar.setVisible(false);
			
			 String username = userfl.getText();
			 String email = emailfl.getText();
			if (username.equals(body.getAuUsername())) {
				//send email with validation code
				if (email.equals(body.getEmail())) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\Screens\\PasswordRestoration.fxml"));
					Parent root;
					try {
						root = loader.load();
					    Scene scene = new Scene(root);
						Stage stage = (Stage) backBtn.getScene().getWindow(); 
						stage.setScene(scene);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				else if (email.equals("")) {
					emailStar.setVisible(true);
					System.out.println("Empty field");
				}
			}
			else if (username.equals("")) {
				userStar.setVisible(true);
				if (email.equals("")) {
					emailStar.setVisible(true);
					System.out.println("Empty field");
				}
			}
			else {
				if (email.equals("")) {
					emailStar.setVisible(true);
					System.out.println("Empty field");
				}
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Message");
			    alert.setHeaderText("Not found");
			    alert.setContentText("User name not found on database");
			    alert.showAndWait();
			}
		});
	}

}