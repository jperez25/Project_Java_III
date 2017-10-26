package login.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JavaLessons {

	@FXML
	private Button backBtn;
	@FXML
	private Button search;
	@FXML
	private Button sort;
	@FXML
	private Button hashing;

	public void initialize() {
		//Go back to the home page screen
		backBtn.setOnAction(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Screens/Home.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) backBtn.getScene().getWindow(); 
				stage.setScene(scene);
			} catch (IOException e1) {
				//Let user know screen was not found
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Screen Error");
				alert.setHeaderText("Screen Not found");
				alert.setContentText("The screen was not found");
				alert.showAndWait();
			}
		});
	}
}
