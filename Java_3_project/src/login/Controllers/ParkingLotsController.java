package login.Controllers;

import java.io.IOException;

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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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

	public void initialize() throws Exception {
		WebEngine webEngine = weather.getEngine();
		webEngine.load("https://www.yahoo.com/news/weather");
		
		String[] lots = {"All lots", "Vago North", "Vago South", "STEM", "Institute", "Southlawn", "Eckhart", "Dunham", "UBH", "Parolini"};
		ObservableList<String> lotList = FXCollections.observableArrayList(lots);
		selectLot.getItems().addAll(lotList);
		
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		ObservableList<String> dayList = FXCollections.observableArrayList(days);
		selectDay.getItems().addAll(dayList);
		
		String[] hours = {"7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "1:00", "2:00", "3:00", "4:00"};
		ObservableList<String> hourList = FXCollections.observableArrayList(hours);
		selectHour.getItems().addAll(hourList);
		
		//There is no need for a back button if there is a log out button
		/*backBtn.setOnAction(e->{
	        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/screens/First_Login.fxml"));
	        Parent root2;
			try {
				root2 = loader2.load();
		        Scene scene = new Scene(root2);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});*/
		
		submitBtn.setOnAction(e->{
	       //get info from database and display on lots info screen
		});
		
		
		logoutBtn.setOnAction(e->{
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
						Stage stage = (Stage) backBtn.getScene().getWindow(); 
						stage.setScene(scene);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			 
		});
	}
}