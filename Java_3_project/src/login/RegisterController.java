package login;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class RegisterController {
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

	 // called by the FXML loader after the labels declared above are injected:
	public void initialize() throws Exception {
		LocalDateStringConverter strcon = new LocalDateStringConverter();
		String dt =  strcon.toString(date.getValue());

		AUBody body = new AUBody();
		regBtn.setOnAction(e->{
			body.setFname(fname.getText());
			body.setLname(lname.getText());
			body.setAuUsername(auUserName.getText());
			body.setPassword(pass.getText());
			body.setEmail(email.getText());
			body.setRegistered_date(dt);
			System.out.println("Succesful");
		});
	}
}
