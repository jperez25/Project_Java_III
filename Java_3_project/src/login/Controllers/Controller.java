package login.Controllers;

import java.io.IOException;
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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import login.Connector;

//imports...

public class Controller {
	// Fxml injections
	@FXML
	private Button bt1;
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
	@FXML
	private Label errorLabel;

	// called by the FXML loader after the labels declared above are injected:
	public void initialize() throws Exception {
		
		// listener
		passfl.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				bt1.fire();
			}
		});


		// When send button is clicked it should grant access to the app
		bt1.setOnAction(e -> {
			userStar.setVisible(false);
			passStar.setVisible(false);
			errorLabel.setVisible(false);
			String username = txtfl.getText();
			String pass = passfl.getText();

			// Check empty fields
			if (username.equals("") || pass.equals("")) {
				errorLabel.setText("Empty Fields");
				errorLabel.setVisible(true);

				if (username.equals("")) {
					userStar.setVisible(true);
				}
				if (pass.equals("")) {
					passStar.setVisible(true);
				}
			}

			else {
				try {
					Connector con = new Connector();
					ResultSet userName = con.execQuery("select UserName from user where UserName= '" + username + "';");
					boolean isEmpty = userName.next();

					// If user is not found
					if (!isEmpty) {
						errorLabel.setText("Wrong Username or password");
						errorLabel.setVisible(true);

					}
					// User is found
					else {
						// If password does not match registers
						ResultSet passW = con.execQuery("select Password from user where Password='" + pass
								+ "' and UserName='" + username + "';");
						boolean isEmptyPass = passW.next();
						if (!isEmptyPass) {
							errorLabel.setText("Wrong Username or password");
							errorLabel.setVisible(true);
						} 
						else {
							// go to pick a lot
							FXMLLoader loader2 = new FXMLLoader(
									getClass().getResource("../screens/ParkingLotsScreen.fxml"));
							Parent root2;
							try {
								root2 = loader2.load();
								Scene scene = new Scene(root2);
								Stage stage = (Stage) bt1.getScene().getWindow();
								stage.setScene(scene);
							} catch (IOException e1) {
								Alert alert = new Alert(Alert.AlertType.ERROR);
								alert.setTitle("Screen Error");
								alert.setHeaderText("Screen Not found");
								alert.setContentText("The screen was not found");
								alert.showAndWait();
							}
						}
					}
				}
				 catch (Exception ex) {
					System.out.println(ex);
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText("Connection Error");
					alert.setContentText("You are not connected to the internet");
					alert.showAndWait();
				}
			} // End of else block
		}// end of event handler
		);
		lnk1.setOnAction(e -> {
			// Go to register Scene
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Screens/RegisterScene.fxml"));
			Parent root2;
			try {
				root2 = loader2.load();
				Scene scene = new Scene(root2);
				Stage stage = (Stage) bt1.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}

		});
		lnk2.setOnAction(e -> {
			// Go to Forgot my password Screen
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Screens/ForgotPassword.fxml"));
			Parent root2;
			try {
				root2 = loader2.load();
				Scene scene = new Scene(root2);
				Stage stage = (Stage) bt1.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}

		});
		lnk1.setFocusTraversable(false);
		lnk2.setFocusTraversable(false);
	}
}