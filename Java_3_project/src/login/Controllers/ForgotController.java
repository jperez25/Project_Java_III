package login.Controllers;

import java.io.IOException;
import java.sql.ResultSet;
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
import login.MainDriver;

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
	@FXML
	private Label errorLabel;

	public void initialize() throws Exception {

		// listener
		emailfl.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				sendBtn.fire();
			}
		});

		/*
		 * When clicked sent back to Login Screen If Screen not found raise alert.
		 */
		backBtn.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/First_login.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) backBtn.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});

		/*
		 * When clicked check if any fields are empty. If they are set stars and error
		 * label visible. If not execute connection and check if values entered match
		 * with our registers in DB if values match sent email if not display error
		 * label and stars. Sent user back to login Screen if email sent successfully.
		 */
		sendBtn.setOnAction(e -> {
			// Set stars to false
			userStar.setVisible(false);
			emailStar.setVisible(false);
			errorLabel.setVisible(false);

			// Get user name and email
			String username = userfl.getText();
			String email = emailfl.getText();

			// check if fields are empty
			if (username.equals("") || email.equals("")) {
				errorLabel.setText("Please fill empty Fields");
				errorLabel.setVisible(true);

				if (username.equals("")) {
					userStar.setVisible(true);
				}
				if (email.equals("")) {
					emailStar.setVisible(true);
				}
			} else {
				try {
					// Execute query
					ResultSet userName = MainDriver.con
							.execQuery("select UserName from user where UserName='" + username + "';");
					boolean isEmpty = userName.next();

					// If user not found returns null
					if (!isEmpty) {
						errorLabel.setText("User Not Found");
						errorLabel.setVisible(true);
					}
					// If user is found check email
					else {
						final String user = "auparkinglot@gmail.com";
						final String pass = "ParkingLot";

						// Execute query for password
						ResultSet eMail = MainDriver.con
								.execQuery("select Email from user where Email='" + email + "';");
						boolean isEmailEmpty = eMail.next();

						// If email does not matche user name
						if (!isEmailEmpty) {
							errorLabel.setText("Email not found");
							errorLabel.setVisible(true);
						}
						// If email matches user name sent email
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
								ResultSet emailConfirm = MainDriver.con
										.execQuery("select email from user where username='" + username + "';");
								emailConfirm.next();

								Message message = new MimeMessage(session);
								message.setFrom(new InternetAddress("auparkinglot@gmail.com")); // Hard coded who's
																								// sending the email
								message.setRecipients(Message.RecipientType.TO,
										InternetAddress.parse(emailConfirm.getString(1))); // Hard coded who the email
																							// is being sent to
								// Can use array or the database after being setup
								message.setSubject("My first email"); // Subject of the email
								ResultSet emailPass = MainDriver.con
										.execQuery("select password from user where username='" + username + "';");
								emailPass.next();
								message.setContent(
										"<h:body style=background-color:white;font-family:verdana; color:#002>" // Uses
																												// html
																												// for a
																												// basic
																												// setup
																												// for
																												// the
																												// email
												+ "You're forgot your password!<br/>Your password is: "
												+ emailPass.getString(1) + "<br/>" // add email password from database
																					// in here
												+ "</body>",
										"text/html; charset=utf-8");
								Transport.send(message); // Sends the message to the user

								Alert alert = new Alert(Alert.AlertType.INFORMATION); // Notify user that email was sent
																						// to their email address
								alert.setTitle("Email sent");
								alert.setHeaderText("Email was sent");
								alert.setContentText(
										"An email was sent to your account with your password.\n Try not to forget it this time.");
								alert.showAndWait();

								// Take user to login screen
								FXMLLoader loader2 = new FXMLLoader(
										getClass().getResource("../Screens/First_Login.fxml"));
								Parent root2;
								try {
									root2 = loader2.load();
									Scene scene = new Scene(root2);
									Stage stage = (Stage) backBtn.getScene().getWindow();
									stage.setScene(scene);
								} catch (IOException e1) {
									Alert alert1 = new Alert(Alert.AlertType.ERROR);
									alert1.setTitle("Screen Error");
									alert1.setHeaderText("Screen Not found");
									alert1.setContentText("The screen was not found");
									alert1.showAndWait();
								}
							}
							// If error happens while sending email
							catch (MessagingException x) {
								Alert alert1 = new Alert(Alert.AlertType.ERROR);
								alert1.setTitle("Email Error");
								alert1.setHeaderText("Email Not Sent");
								alert1.setContentText("Something went wrong while sending your email");
								alert1.showAndWait();
							}

						}
					}
				}
				// If screen not found
				catch (Exception a) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText("Connection Error");
					alert.setContentText("You are not connected to the internet");
					alert.showAndWait();
				}
			}

		});

		/*
		 * Just if we change our minds sent code with verification number
		 */
		verifyBtn.setOnAction(e -> {
			ogPane.setVisible(false);
			codePane.setLayoutY(ogPane.getLayoutY());
			codePane.setLayoutX(ogPane.getLayoutX());
			codePane.setVisible(true);

		});
		// Tab does not affect
		userfl.setFocusTraversable(true);
		emailfl.setFocusTraversable(true);
	}

}