package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ParkingLotsController {
	@FXML
	private Button backBtn;
	@FXML
	private Label IceLot;
	@FXML
	private Label stemLot;
	@FXML
	private Label vagoSTLot;
	@FXML
	private Label vagoNTLot;

	public void initialize() throws Exception {
		
		backBtn.setOnAction(e->{
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
		});
		IceLot.setOnMouseClicked(e->{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Message");
		    alert.setHeaderText("Display info");
		    alert.setContentText("We will show a lot of info about this parking lot\n just wait until our new vesion is released.");
		    alert.showAndWait();
		    
		    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/screens/ParkingInfoScreen.fxml"));
	        Parent root2;
			try {
				root2 = loader2.load();
		        Scene scene = new Scene(root2);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		stemLot.setOnMouseClicked(e->{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Message");
		    alert.setHeaderText("Display info");
		    alert.setContentText("We will show a lot of info about this parking lot\n just wait until our new vesion is released.");
		    alert.showAndWait();
		});
		vagoNTLot.setOnMouseClicked(e->{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Message");
		    alert.setHeaderText("Display info");
		    alert.setContentText("We will show a lot of info about this parking lot\n just wait until our new vesion is released.");
		    alert.showAndWait();
		});
		vagoSTLot.setOnMouseClicked(e->{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Message");
		    alert.setHeaderText("Display info");
		    alert.setContentText("We will show a lot of info about this parking lot\n just wait until our new vesion is released.");
		    alert.showAndWait();
		});
	}
}