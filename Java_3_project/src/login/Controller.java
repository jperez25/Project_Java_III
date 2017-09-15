package login;

import java.io.IOException;
import java.util.HashMap;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//imports...

public class Controller {
	 
 @FXML
 private Button bt1 ;
 @FXML
 private TextField txtfl;
 @FXML
 private PasswordField passfl;
 @FXML
 private Hyperlink lnk1;

 // called by the FXML loader after the labels declared above are injected:
	 public void initialize() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		 map.put("jojo","123456");
		 
		 
	     // do initialization and configuration work...
	
	     // When send button is clicked it should grant access to the app
		bt1.setOnAction(e->{
			 String username = txtfl.getText();
			 String pass = passfl.getText();
			 
			 //If user name and password match let user login
			 if (map.containsKey(username)) {
				 txtfl.setText("Just a test");
				 if (map.containsValue(pass)) {
					//switch scenes
					 
				 }
				 else {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
					    alert.setTitle("Message");
					    alert.setHeaderText("Wrong password");
					    alert.setContentText("The password is wrong");
					    alert.showAndWait();
				}
				
			 }
			 else if (username.equals("")) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
				    alert.setTitle("Message");
				    alert.setHeaderText("Empty Field");
				    alert.setContentText("The username filed is empty, try again");
				    alert.showAndWait();
			}
			 else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
				    alert.setTitle("Message");
				    alert.setHeaderText("Wrong username or password");
				    alert.setContentText("The username or the password is wrong");
				    alert.showAndWait();
			}
			 	
			 
			 
		 }
		);
		lnk1.setOnAction(e->{
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("RegisterScene.fxml"));
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
	}
}