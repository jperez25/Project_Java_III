package login;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MainDriver extends Application {
	public static Connector con;
    @Override
    public void start(Stage primaryStage){

        // just load fxml file and display it in the stage:

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screens/WelcomeScreen.fxml"));
        Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Dude, Where's my spot?");
	        primaryStage.show();
		} 
		catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Screen Error");
			alert.setHeaderText("Screen Not found");
			alert.setContentText("The screen was not found");
			alert.showAndWait();
		}
    }

    // main method to support non-JavaFX-aware environments:

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // starts the FX toolkit, instantiates this class, 
        // and calls start(...) on the FX Application thread:
    	//Single connection object
    	con = new Connector();
        launch(args); 
    }
}
