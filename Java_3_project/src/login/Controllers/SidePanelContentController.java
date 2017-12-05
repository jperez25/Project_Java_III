package login.Controllers;

import com.jfoenix.controls.JFXButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import login.MainDriver;

public class SidePanelContentController implements Initializable {

	@FXML
	private JFXButton b1;
	@FXML
	private JFXButton b2;
	@FXML
	private JFXButton b3;
	@FXML
	private JFXButton b4;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		b1.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/RegisterScene.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) b1.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});

		b2.setOnAction(e -> {
			try {
				File fl = new File("Cookies/cookies.txt");
				FileReader cookies = new FileReader(fl);
				System.out.println("in check");
				BufferedReader cook = new BufferedReader(cookies);
				
				
				if (fl.length() != 0) {
					String[]  fromfile = cook.readLine().split(" ");
					String hashedUserName =   fromfile[0];
					String hashedPassword = fromfile[1];
					
					ResultSet userName = MainDriver.con.execQuery("select UserName from user where UserName= '" + hashedUserName + "';");
					userName.next();
					String user = userName.getString(1);
					System.out.println(user);
					
					ResultSet password = MainDriver.con.execQuery("select Password from user where Password='" +  hashedPassword + "';");
					password.next();
					String pass = password.getString(1);
					System.out.println(pass);
					
					
					if (user.equals(hashedUserName) && pass.equals(hashedPassword)) {
						
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Sesion still active");
						alert.setHeaderText("An active sesion was found");
						alert.showAndWait();
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/ParkingLotsScreen.fxml"));
						Parent root;
						try {
							root = loader.load();
							Scene scene = new Scene(root);
							Stage stage = (Stage) b2.getScene().getWindow();
							stage.setScene(scene);
						} catch (IOException e1) {
							System.out.println(e1);
							Alert alert1 = new Alert(Alert.AlertType.ERROR);
							alert1.setTitle("Screen Error");
							alert1.setHeaderText("Screen Not found");
							alert1.setContentText("The screen was not found");
							alert1.showAndWait();
						}
					}
				}
				else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/First_login.fxml"));
					Parent root;
					try {
						root = loader.load();
						Scene scene = new Scene(root);
						Stage stage = (Stage) b2.getScene().getWindow();
						stage.setScene(scene);
					} catch (IOException e1) {
						System.out.println(e1);
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Screen Error");
						alert.setHeaderText("Screen Not found");
						alert.setContentText("The screen was not found");
						alert.showAndWait();
					}
				}
				
				
				
				
				cook.close();
				cookies.close();
				
			} catch (Exception e2) {
				System.out.println(e2);
				System.out.println("Couldn't write to the file"+ e2);
			}
			
			
		});

		b3.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/Events.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) b3.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e1) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});

		b4.setOnAction(e -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/JavaLessons.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) b3.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});
	}
}
