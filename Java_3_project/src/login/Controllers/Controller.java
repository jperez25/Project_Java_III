package login.Controllers;

//imports...

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import login.MainDriver;

public class Controller implements Initializable{
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
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXHamburger hamburger;

	// called by the FXML loader after the labels declared above are injected:
	public void initialize(URL url, ResourceBundle rb) {
		
		//Loads the side panel after the hamburger is pressed
				try {
					VBox box = FXMLLoader.load(getClass().getResource("../Screens/SidePanelContent.fxml"));
					drawer.setSidePane(box);
				} catch (IOException ex) {
					Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
				}
				//Transition used to create the effect when hamburger is pressed
				HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
				transition.setRate(-1);
				//Event handler when hamburger pressed
				hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
					transition.setRate(transition.getRate()*-1);
					transition.play();

					if(drawer.isShown())
					{
						drawer.close();
					}else
						drawer.open();
				});
		
		// listener
		passfl.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				bt1.fire();
			}
		});


		/*when clicked check if fields are empty and make stars and
		 * message visible.
		 * If fields are fill execute query, if user and password match 
		 * with our registers in the data base let user go to parking lots scene.
		 * If not display error label.
		 */
		bt1.setOnAction(e -> {
			//hide stars and labels
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
			
			//if fields are not empty
			else {
				try {
					//Execute query
					ResultSet userName = MainDriver.con.execQuery("select UserName from user where UserName= '" + username + "';");
					boolean isEmpty = userName.next();

					// If user is not found
					if (!isEmpty) {
						errorLabel.setText("Wrong Username or password");
						errorLabel.setVisible(true);

					}
					// User is found
					else {
						//Execute query for password
						ResultSet passW = MainDriver.con.execQuery("select Password from user where Password='" + pass
								+ "' and UserName='" + username + "';");
						boolean isEmptyPass = passW.next();
						// If password does not match registers
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
				//Data base connection error
				 catch (Exception ex) {
					System.out.println(ex);
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText("Connection Error");
					alert.setContentText("You are not connected to the internet");
					alert.showAndWait();
				}
			} 
		});
		
		/*When clicked
		 * sent to Register Scene
		 * if scene not found alert is raised
		 */
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
		
		/*When clicked
		 * sent to Forgot password Scene
		 * if Scene not found alert is raised
		 */
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
		//tab does not affect
		lnk1.setFocusTraversable(false);
		lnk2.setFocusTraversable(false);
	}
}