package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import login.AuBody;

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
	private TextField email;
	@FXML
	private DatePicker date;
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
	private Label emailStar;
	@FXML
	private Label dateStar;
	 
	public void initialize() throws Exception {
		 //There is a bug Check it later    <<<---------------------------------------------------------
		LocalDateStringConverter strcon = new LocalDateStringConverter();
		String dt =  strcon.toString(date.getValue());

		AuBody body = new AuBody();
		regBtn.setOnAction(e->{
			
			System.out.println(dt+"It has nothing");
			
			fnameStar.setVisible(false);
			lnameStar.setVisible(false);
			auUserStar.setVisible(false);
			passwordStar.setVisible(false);
			passwordStar.setVisible(false);
			emailStar.setVisible(false);
			dateStar.setVisible(false);
			
			if (fname.getText().equals("") || lname.getText().equals("") || auUserName.getText().equals("") || pass.getText().equals("") || email.getText().equals("") || date.equals(null)) {
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
			    if (email.getText().equals("")) {
					emailStar.setVisible(true);
				}
			    //There is a bug Check it later    <<<---------------------
			    if (date.equals(null)) {
					dateStar.setVisible(true);
				}
			    
			}
			else {
				body.setFname(fname.getText());
				body.setLname(lname.getText());
				body.setAuUsername(auUserName.getText());
				body.setPassword(pass.getText());
				body.setEmail(email.getText());
				body.setRegistered_date(dt);
				System.out.println("Succesful");
			}
		});
		backBtn.setOnAction(e->{
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/First_login.fxml"));
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
}