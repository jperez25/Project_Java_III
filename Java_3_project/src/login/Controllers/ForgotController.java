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
import login.Connector;

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

		// Check user and email
		// send email with recovery code
		sendBtn.setOnAction(e -> {
			// set stars to false
			userStar.setVisible(false);
			emailStar.setVisible(false);
			errorLabel.setVisible(false);

			String username = userfl.getText();
			String email = emailfl.getText();

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
					Connector con = new Connector();
					// If user not found returns null
					ResultSet userName = con
							.execQuery("select UserName from user where UserName='" + username + "';");
					boolean isEmpty = userName.next();

					if (!isEmpty) {
						errorLabel.setText("User Not Found");
						errorLabel.setVisible(true);
					} else {
						final String user = "auparkinglot@gmail.com";
						final String pass = "ParkingLot";

						ResultSet eMail = con.execQuery("select Email from user where Email='" + email + "';");
						boolean isEmailEmpty = eMail.next();
						if (!isEmailEmpty) {
							errorLabel.setText("Email not found");
							errorLabel.setVisible(true);
						} else {
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
								ResultSet emailConfirm = con
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
								ResultSet emailPass = con
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
												+ "You're resetting your email!<br/>Your email is: "
												+ emailPass.getString(1) + "<br/>" // add email password from database
																					// in here
												+ "</body>",
										"text/html; charset=utf-8");
								Transport.send(message); // Sends the message to the user

								Alert alert = new Alert(Alert.AlertType.INFORMATION); // Notify user that email was sent to their email address
								alert.setTitle("Email sent");
								alert.setHeaderText("Email was sent");
								alert.setContentText("An email was sent to your account with your password.\n Try not to forget it this time.");
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
							} catch (MessagingException x) {
								Alert alert1 = new Alert(Alert.AlertType.ERROR);
								alert1.setTitle("Email Error");
								alert1.setHeaderText("Email Not Sent");
								alert1.setContentText("Something went wrong while sending your email");
								alert1.showAndWait();
							}

						}
					}
				} catch (Exception a) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText("Connection Error");
					alert.setContentText("You are not connected to the internet");
					alert.showAndWait();
				}
			}

		});
		// Check code and compare with the code send to user
		verifyBtn.setOnAction(e -> {
			ogPane.setVisible(false);
			codePane.setLayoutY(ogPane.getLayoutY());
			codePane.setLayoutX(ogPane.getLayoutX());
			codePane.setVisible(true);

		});
		userfl.setFocusTraversable(true);
		emailfl.setFocusTraversable(true);
	}

}