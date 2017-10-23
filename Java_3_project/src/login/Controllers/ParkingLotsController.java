package login.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

import com.mysql.jdbc.Buffer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import login.Connector;
import login.MainDriver;

public class ParkingLotsController {
	@FXML
	private Button backBtn;
	@FXML
	private ComboBox<String> selectDay;
	@FXML
	private ComboBox<String> selectHour;
	@FXML
	private Hyperlink logoutBtn;
	@FXML
	private ComboBox<String> selectLot;
	@FXML
	private Button submitBtn;
	@FXML
	private WebView weather;
	@FXML
	private ImageView lotImage;
	@FXML
	private Label spotsAv;
	@FXML
	private Labeled hoursAv;

	public void initialize() throws Exception {
		WebEngine webEngine = weather.getEngine();
		webEngine.load("https://www.yahoo.com/news/weather");

		/*
		 * Below are all the lists needed to populate the combo boxes.
		 */
		String[] lots = { "All lots", "Vago North", "Vago South", "STEM", "Institute", "Southlawn", "Eckhart", "Dunham",
				"UBH", "Parolini" };
		ObservableList<String> lotList = FXCollections.observableArrayList(lots);
		selectLot.getItems().addAll(lotList);

		String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		ObservableList<String> dayList = FXCollections.observableArrayList(days);
		selectDay.getItems().addAll(dayList);

		String[] hours = { "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "4:00" };
		ObservableList<String> hourList = FXCollections.observableArrayList(hours);
		selectHour.getItems().addAll(hourList);

		
		/*
		 * The next two Event Handlers make sure the user is user is not able to select
		 * an option from the combo box without first choosing an option from the
		 * previous combo box.
		 */
		selectLot.setOnAction(e -> {
			/*On selection display images
			 * if user selects all lots display image, but don't able next combo Box
			 * if user selects other than all lots display image and able the next combo box
			 */
			ResultSet image;
			try {
				if (selectLot.getValue().equals("All lots")) {				
					Image pic = new Image("/images/allLots.png");
					lotImage.setImage(pic);
				}
				else {
					image = MainDriver.con.execQuery("select images from spaces where lot_id = '"+selectLot.getValue()+"';");
					image.next();
					System.out.println(image.getString(1));
					Image pic = new Image(image.getString(1));
					lotImage.setImage(pic);
				}
			} catch (SQLException e1) {
				System.out.println(e1);
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Img Error");
				alert.setHeaderText("Image not found");
				alert.showAndWait();
			}

			if (selectLot.getValue().equals("All lots")) {
				selectDay.setDisable(true);
			} else {
				selectDay.setDisable(false);
			}
		});
		selectDay.setOnAction(e -> {
			if (selectDay.getValue().equals("")) {
				selectHour.setDisable(true);
			} else {
				selectHour.setDisable(false);
			}
		});
		
		

		/*When submit button is clicked it checks if hour combo box is 
		 * selected if not it tells the user to select an option.
		 * If user choose an option get the time selected and execute
		 * a query to the database for the information asked.
		 * exception is raised if there is something wrong with
		 *  the connection to the database.
		*/
		submitBtn.setOnAction(e -> {
			// get info from database and display on lots info screen
			if (selectHour.getValue() == null) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Message");
				alert.setHeaderText("Please pick a lot,date and/or time");
				alert.showAndWait();
			}
			//if not null
			else {
				//cut the zeros from the hour
				String[] cutZeros = selectHour.getValue().split(":");
				String hour = cutZeros[0];
				try {
					//Execute query for number of spots at a certain time
					ResultSet spots = MainDriver.con.execQuery("select spots_at_" + hour + " from spaces where lot_id = '"
							+ selectLot.getValue() + "' and day = '" + selectDay.getValue() + "';");
					spots.next();
					spotsAv.setText(spots.getString(1));
					
					//Execute query for Service time
					ResultSet hoursA = MainDriver.con.execQuery(
							"select hours_of_service from spaces where lot_id = '" + selectLot.getValue() + "';");
					hoursA.next();
					hoursAv.setText(hoursA.getString(1));
				} catch (Exception e1) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText("Connection Error");
					alert.setContentText("You are not connected to the internet");
					alert.showAndWait();
				}
			}
		});

		
		
		/*
		 * When clicked the user is going to be asked if it is sure to log out, if yes
		 * take user to login Screen. If no let user stay on current screen.
		 * If there is any error loading a screen an Exception is raised an alert is displayed.
		 */
		logoutBtn.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Message");
			alert.setHeaderText("Are you sure you want to logout?");
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../Screens/First_Login.fxml"));
					Parent root2;
					try {
						root2 = loader2.load();
						Scene scene = new Scene(root2);
						Stage stage = (Stage) logoutBtn.getScene().getWindow();
						stage.setScene(scene);
					} catch (IOException e1) {
						Alert alert1 = new Alert(Alert.AlertType.ERROR);
						alert1.setTitle("Screen Error");
						alert1.setHeaderText("Screen Not found");
						alert1.setContentText("The screen was not found");
						alert1.showAndWait();
					}
				}
			});

		});
	}
}